package com.example.railora.ui.auth

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.adapters.OnboardingAdapter
import com.example.railora.databinding.FragmentOnboardingBinding
import com.example.railora.models.OnboardingItem

/**
 * Handles the complete onboarding flow.
 *
 * Responsibilities:
 * - Creates onboarding pages
 * - Sets up ViewPager2
 * - Updates indicators
 * - Handles Back / Skip / Continue / Get Started
 * - Navigates to LoginFragment
 */
class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private lateinit var onboardingItems: List<OnboardingItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnboardingBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnboardingItems()

        setupViewPager()

        setupButtonListeners()
    }

    /**
     * Creates the 6 onboarding pages.
     */
    private fun setupOnboardingItems() {

        onboardingItems = listOf(

            OnboardingItem(
                R.drawable.onboarding_1,
                getString(R.string.onboarding_title_1),
                getString(R.string.onboarding_desc_1)
            ),

            OnboardingItem(
                R.drawable.onboarding_2,
                getString(R.string.onboarding_title_2),
                getString(R.string.onboarding_desc_2)
            ),

            OnboardingItem(
                R.drawable.onboarding_3,
                getString(R.string.onboarding_title_3),
                getString(R.string.onboarding_desc_3)
            ),

            OnboardingItem(
                R.drawable.onboarding_4,
                getString(R.string.onboarding_title_4),
                getString(R.string.onboarding_desc_4)
            ),

            OnboardingItem(
                R.drawable.onboarding_5,
                getString(R.string.onboarding_title_5),
                getString(R.string.onboarding_desc_5)
            ),

            OnboardingItem(
                R.drawable.onboarding_6,
                getString(R.string.onboarding_title_6),
                getString(R.string.onboarding_desc_6)
            )
        )
    }

    /**
     * Connects ViewPager2 with the adapter and indicators.
     */
    private fun setupViewPager() {

        val adapter = OnboardingAdapter(onboardingItems)

        binding.viewPagerOnboarding.adapter = adapter

        binding.indicator.setViewPager(binding.viewPagerOnboarding)

        binding.viewPagerOnboarding.registerOnPageChangeCallback(
            object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    updateUI(position)
                }
            }
        )

        updateUI(0)
    }

    /**
     * Handles Back, Skip and Continue button clicks.
     */
    private fun setupButtonListeners() {

        binding.textBack.setOnClickListener {

            val currentPage = binding.viewPagerOnboarding.currentItem

            if (currentPage > 0) {

                binding.viewPagerOnboarding.currentItem =
                    currentPage - 1
            }
        }

        binding.textSkip.setOnClickListener {

            navigateToLogin()
        }

        binding.buttonContinue.setOnClickListener {

            val currentPage = binding.viewPagerOnboarding.currentItem

            if (currentPage == onboardingItems.lastIndex) {

                navigateToLogin()

            } else {

                binding.viewPagerOnboarding.currentItem =
                    currentPage + 1
            }
        }
    }

    /**
     * Updates the button texts and visibility
     * based on the current page.
     */
    private fun updateUI(position: Int) {

        val item = onboardingItems[position]

        // Update Card Content
        binding.textTitle.text = item.title
        binding.textDescription.text = item.description

        // Back Button
        binding.textBack.isVisible = position != 0

        // Skip Button
        val isLastPage = position == onboardingItems.lastIndex
        binding.textSkip.isVisible = !isLastPage

        // Continue / Get Started
        binding.buttonContinue.text =
            if (isLastPage) {
                getString(R.string.get_started)
            } else {
                getString(R.string.continue_text)
            }

        if (isLastPage) {
            binding.buttonContinue.setTextColor(
                ContextCompat.getColor(requireContext(), android.R.color.white)
            )

            binding.buttonContinue.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(requireContext(), R.color.railora_orange_end)
                )

        } else {

            binding.buttonContinue.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.onboarding_button_text)
            )

            binding.buttonContinue.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(requireContext(), R.color.onboarding_button_background)
                )
        }
    }

    /**
     * Navigates to LoginFragment.
     */
    private fun navigateToLogin() {

        findNavController().navigate(
            R.id.action_onboardingFragment_to_loginFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}