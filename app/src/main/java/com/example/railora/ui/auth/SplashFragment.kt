package com.example.railora.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Splash screen shown for 2 seconds when the app launches.
 *
 * Why a Fragment instead of SplashActivity?
 * - AuthActivity already hosts the entire auth flow via Navigation Component.
 * - Keeping splash as a fragment avoids an extra activity and keeps all auth
 *   screens in one NavGraph (Splash → Onboarding → Login → MainActivity later).
 */
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation is started here (not in onCreateView) because:
        // - The view hierarchy and NavController are ready after the view is created.
        // - viewLifecycleOwner is available, so coroutines cancel if the user leaves early.
        startSplashTimer()
    }

    /**
     * Waits exactly 2 seconds, then navigates to OnboardingFragment.
     *
     * How the delay works:
     * - lifecycleScope.launch starts a coroutine tied to the fragment's lifecycle.
     * - delay(2000) suspends for 2000 ms without blocking the main thread.
     * - After 2 seconds, findNavController().navigate() moves to onboarding.
     * - If the fragment is destroyed before 2 seconds, the coroutine is cancelled.
     */
    private fun startSplashTimer() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(SPLASH_DURATION_MS)
            findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPLASH_DURATION_MS = 2000L
    }
}
