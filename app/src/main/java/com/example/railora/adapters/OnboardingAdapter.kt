package com.example.railora.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.railora.databinding.ItemOnboardingBinding
import com.example.railora.models.OnboardingItem

/**
 * Adapter used by ViewPager2 to display onboarding screens.
 *
 * Flow:
 * ViewPager2
 *     ↓
 * OnboardingAdapter
 *     ↓
 * item_onboarding.xml
 *     ↓
 * OnboardingItem data
 *
 * It reuses the same layout for all onboarding pages and simply changes
 * the image, title, and description.
 */
class OnboardingAdapter(
    private val onboardingItems: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    /**
     * Holds references to the views inside item_onboarding.xml.
     * This avoids repeatedly calling findViewById().
     */
    inner class OnboardingViewHolder(
        private val binding: ItemOnboardingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the data from an OnboardingItem to the UI.
         */
        fun bind(item: OnboardingItem) {
            binding.imageOnboarding.setImageResource(item.imageResId)
        }
    }

    /**
     * Called when ViewPager2 needs a new page.
     *
     * Inflate item_onboarding.xml using View Binding.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingViewHolder {

        val binding = ItemOnboardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OnboardingViewHolder(binding)
    }

    /**
     * Called whenever a page needs data.
     *
     * Example:
     * position = 0 → Page 1
     * position = 1 → Page 2
     * ...
     */
    override fun onBindViewHolder(
        holder: OnboardingViewHolder,
        position: Int
    ) {
        holder.bind(onboardingItems[position])
    }

    /**
     * Total number of onboarding pages.
     */
    override fun getItemCount(): Int {
        return onboardingItems.size
    }
}