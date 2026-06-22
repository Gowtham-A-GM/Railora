package com.example.railora.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.railora.R
import com.example.railora.databinding.FragmentProfileBinding
import com.example.railora.utils.BookingManager

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var isBookingsExpanded = false
    private var isPassengersExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(
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
        super.onViewCreated(
            view,
            savedInstanceState
        )

        setupBookings()
        setupPassengers()
        setupClicks()
    }

    private fun setupBookings() {

        binding.tvBookedTrainName.text =
            "${BookingManager.trainName} (${BookingManager.trainNumber})"

        binding.tvBookedRoute.text =
            "${BookingManager.fromStation} → ${BookingManager.toStation}"

        binding.tvBookedDate.text =
            BookingManager.journeyDate

        binding.tvBookedClass.text =
            BookingManager.selectedClass
    }

    private fun setupPassengers() {

        binding.layoutPassengersContent.removeAllViews()

        BookingManager.selectedPassengers.forEach { passenger ->

            val passengerView = layoutInflater.inflate(
                R.layout.item_review_passenger,
                binding.layoutPassengersContent,
                false
            )

            val tvName =
                passengerView.findViewById<TextView>(
                    R.id.tvPassengerName
                )

            val tvDetails =
                passengerView.findViewById<TextView>(
                    R.id.tvPassengerDetails
                )

            tvName.text =
                "${passenger.firstName} ${passenger.lastName}"

            tvDetails.text =
                "${passenger.age} Years • ${passenger.gender} • ${passenger.berthPreference}"

            binding.layoutPassengersContent.addView(
                passengerView
            )
        }
    }

    private fun setupClicks() {

        binding.layoutBookingsHeader.setOnClickListener {

            isBookingsExpanded =
                !isBookingsExpanded

            binding.layoutBookingsContent.visibility =
                if (isBookingsExpanded)
                    View.VISIBLE
                else
                    View.GONE

            binding.ivBookingsArrow.rotation =
                if (isBookingsExpanded) 180f else 0f
        }

        binding.layoutPassengersHeader.setOnClickListener {

            isPassengersExpanded =
                !isPassengersExpanded

            binding.layoutPassengersContent.visibility =
                if (isPassengersExpanded)
                    View.VISIBLE
                else
                    View.GONE

            binding.ivPassengersArrow.rotation =
                if (isPassengersExpanded) 180f else 0f
        }

        binding.layoutTerms.setOnClickListener {

            Toast.makeText(
                requireContext(),
                "Terms of Service Coming Soon",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnGoHome.setOnClickListener {

            Toast.makeText(
                requireContext(),
                "Logout Coming Soon",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}