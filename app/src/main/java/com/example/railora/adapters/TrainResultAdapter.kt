package com.example.railora.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railora.databinding.ItemTrainResultBinding
import com.example.railora.models.TrainClass
import com.example.railora.models.TrainResult

class TrainResultAdapter(
    private val trainList: List<TrainResult>,
    private val onClassClick: (TrainResult, TrainClass) -> Unit
) : RecyclerView.Adapter<TrainResultAdapter.TrainResultViewHolder>() {

    inner class TrainResultViewHolder(
        private val binding: ItemTrainResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(train: TrainResult) {

            binding.tvTrainName.text =
                "${train.trainName} (${train.trainNumber})"

            binding.tvDepartureTime.text = train.departureTime
            binding.tvArrivalTime.text = train.arrivalTime
            binding.tvDuration.text = train.duration

            binding.tvSource.text = train.source
            binding.tvDestination.text = train.destination

            binding.rvClasses.layoutManager =
                LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

            binding.rvClasses.adapter =
                TrainClassAdapter(train.classes) { trainClass ->
                    onClassClick(train, trainClass)
                }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainResultViewHolder {

        val binding = ItemTrainResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TrainResultViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TrainResultViewHolder,
        position: Int
    ) {
        holder.bind(trainList[position])
    }

    override fun getItemCount() = trainList.size
}