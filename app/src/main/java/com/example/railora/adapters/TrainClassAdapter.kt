package com.example.railora.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.railora.databinding.ItemTrainClassBinding
import com.example.railora.models.TrainClass

class TrainClassAdapter(
    private val classList: List<TrainClass>,
    private val onClassClick: (TrainClass) -> Unit
) : RecyclerView.Adapter<TrainClassAdapter.TrainClassViewHolder>() {

    inner class TrainClassViewHolder(
        private val binding: ItemTrainClassBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trainClass: TrainClass) {

            binding.tvClassCode.text = trainClass.classCode
            binding.tvFare.text = trainClass.fare
            binding.tvAvailability.text = trainClass.availability

            binding.root.setOnClickListener {
                onClassClick(trainClass)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainClassViewHolder {

        val binding = ItemTrainClassBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TrainClassViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TrainClassViewHolder,
        position: Int
    ) {
        holder.bind(classList[position])
    }

    override fun getItemCount() = classList.size
}