package com.example.railora.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.railora.databinding.ItemPassengerBinding
import com.example.railora.models.Passenger

class PassengerAdapter(
    private val passengerList: List<Passenger>,
    private val onSelectionChanged: () -> Unit
) : RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder>() {

    inner class PassengerViewHolder(
        private val binding: ItemPassengerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(passenger: Passenger) {

            binding.cbPassenger.text =
                "${passenger.firstName} ${passenger.lastName}"

            binding.cbPassenger.isChecked =
                passenger.isSelected

            binding.cbPassenger.setOnCheckedChangeListener { _, isChecked ->

                passenger.isSelected = isChecked

                onSelectionChanged()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengerViewHolder {

        return PassengerViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PassengerViewHolder,
        position: Int
    ) {
        holder.bind(passengerList[position])
    }

    override fun getItemCount() = passengerList.size
}