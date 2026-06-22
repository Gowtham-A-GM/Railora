package com.example.railora.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.databinding.FragmentTicketBookedBinding
import com.example.railora.utils.BookingManager

class TicketBookedFragment : Fragment() {

    private var _binding: FragmentTicketBookedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentTicketBookedBinding.inflate(
                inflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {

                findNavController().navigate(
                    R.id.homeFragment,
                    null,
                    androidx.navigation.NavOptions.Builder()
                        .setPopUpTo(
                            R.id.homeFragment,
                            false
                        )
                        .build()
                )
            }

        binding.tvTrainNumber.text =
            BookingManager.trainNumber

        binding.tvTrainName.text =
            BookingManager.trainName

        binding.tvJourneyDate.text =
            BookingManager.journeyDate

        binding.tvFromStation.text =
            BookingManager.fromStation

        binding.tvToStation.text =
            BookingManager.toStation

        binding.tvDepartureTime.text =
            BookingManager.departureTime

        binding.tvArrivalTime.text =
            BookingManager.arrivalTime

        binding.tvClass.text =
            BookingManager.selectedClass

        binding.tvStatus.text = "CNF"

        binding.tvPnr.text =
            (1000000000..9999999999)
                .random()
                .toString()

        binding.tvSeat.text = "12C"

        binding.btnGoHome.setOnClickListener {

            findNavController().navigate(
                R.id.homeFragment,
                null,
                androidx.navigation.NavOptions.Builder()
                    .setPopUpTo(
                        R.id.homeFragment,
                        false
                    )
                    .build()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}