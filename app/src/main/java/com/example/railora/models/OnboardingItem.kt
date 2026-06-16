package com.example.railora.models

/**
 * Represents ONE onboarding page shown inside ViewPager2.
 *
 * Each onboarding screen consists of:
 * - imageResId   → Illustration/image for the page
 * - title        → Main heading
 * - description  → Supporting text below the heading
 *
 * Example:
 * Page 1 → Hey there, traveler!
 * Page 2 → Book Your Tickets
 * ...
 * Page 6 → Sign Up in a Snap!
 */

data class OnboardingItem(
    val imageResId: Int,
    val title: String,
    val description: String
)