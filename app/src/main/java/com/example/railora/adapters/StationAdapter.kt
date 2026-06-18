package com.example.railora.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.railora.databinding.ItemStationBinding
import com.example.railora.models.Station

class StationAdapter(
    private var stationList: List<Station>,
    private val onStationClick: (Station) -> Unit
) : RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

    fun updateStations(
        newStationList: List<Station>
    ) {
        stationList = newStationList
        notifyDataSetChanged()
    }

    inner class StationViewHolder(
        private val binding: ItemStationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(station: Station) {

            binding.tvStationName.text =
                station.stationName

            binding.tvStationCode.text =
                station.stationCode

            binding.root.setOnClickListener {
                onStationClick(station)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StationViewHolder {

        val binding = ItemStationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return StationViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StationViewHolder,
        position: Int
    ) {
        holder.bind(stationList[position])
    }

    override fun getItemCount(): Int {
        return stationList.size
    }
}