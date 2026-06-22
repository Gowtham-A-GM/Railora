package com.example.railora.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.databinding.FragmentReviewJourneyBinding
import com.example.railora.databinding.ItemReviewPassengerBinding
import com.example.railora.utils.BookingManager

class ReviewJourneyFragment : Fragment() {

    private var _binding: FragmentReviewJourneyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReviewJourneyBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ){
        binding.tvBack.setOnClickListener {
            findNavController().navigateUp()
        }

        android.util.Log.d(
            "BOOKING",
            "FROM=${BookingManager.fromStation} TO=${BookingManager.toStation}"
        )

        binding.tvTrainName.text =
            "${BookingManager.trainName} (${BookingManager.trainNumber})"

        binding.tvFromStation.text =
            BookingManager.fromStation

        binding.tvToStation.text =
            BookingManager.toStation

        binding.tvDepartureTime.text =
            BookingManager.departureTime

        binding.tvArrivalTime.text =
            BookingManager.arrivalTime

        binding.tvJourneyDate.text =
            BookingManager.journeyDate

        binding.tvClass.text =
            "${BookingManager.selectedClass} Class"

        BookingManager.selectedPassengers.forEach { passenger ->

            val passengerBinding =
                ItemReviewPassengerBinding.inflate(
                    layoutInflater,
                    binding.layoutPassengers,
                    false
                )

            passengerBinding.tvPassengerName.text =
                "${passenger.firstName} ${passenger.lastName}"

            passengerBinding.tvPassengerDetails.text =
                "${passenger.age} Years • ${passenger.gender} • ${passenger.berthPreference}"

            binding.layoutPassengers.addView(
                passengerBinding.root
            )
        }
        val farePerPassenger =
            BookingManager.fare
                .replace("₹", "")
                .toInt()

        val passengerCount =
            BookingManager.selectedPassengers.size

        val baseFare =
            farePerPassenger * passengerCount

        val reservationCharge =
            40 * passengerCount

        val gst =
            25 * passengerCount

        val totalFare =
            baseFare +
                    reservationCharge +
                    gst

        binding.tvBaseFare.text =
            "₹$baseFare"

        binding.tvReservationCharge.text =
            "₹$reservationCharge"

        binding.tvGst.text =
            "₹$gst"

        binding.tvTotalFare.text =
            "₹$totalFare"



        binding.btnProceedToPay.setOnClickListener {

            findNavController().navigate(
                R.id.ticketBookedFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}