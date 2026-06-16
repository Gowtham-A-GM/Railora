package com.example.railora.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.railora.databinding.ActivityAuthBinding

/**
 * Host activity for the authentication flow (Splash → Onboarding → Login).
 *
 * We use a single activity with fragments instead of separate activities for each
 * screen because the Navigation Component manages transitions within one NavHost.
 * Splash is a [SplashFragment] here—not a dedicated SplashActivity—so the auth
 * flow stays in one place and we avoid an extra activity launch.
 */
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Draw behind the status bar so the splash gradient fills the full screen.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Light (white) status bar icons for readability on the orange gradient.
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
    }
}
