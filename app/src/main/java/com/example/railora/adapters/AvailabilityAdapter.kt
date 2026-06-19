package com.example.railora.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.railora.databinding.ItemAvailabilityDayBinding
import com.example.railora.models.AvailabilityDay

class AvailabilityAdapter(
    private val availabilityList: List<AvailabilityDay>
) : RecyclerView.Adapter<AvailabilityAdapter.AvailabilityViewHolder>() {

    inner class AvailabilityViewHolder(
        private val binding: ItemAvailabilityDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AvailabilityDay) {

            binding.tvDateDay.text =
                "${item.date} ${item.day}"

            binding.tvStatus.text =
                item.status
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AvailabilityViewHolder {

        val binding = ItemAvailabilityDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AvailabilityViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AvailabilityViewHolder,
        position: Int
    ) {
        holder.bind(availabilityList[position])
    }

    override fun getItemCount(): Int {
        return availabilityList.size
    }
}