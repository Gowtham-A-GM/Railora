package com.example.railora.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.databinding.BottomSheetPassengerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.railora.adapters.PassengerAdapter
import com.example.railora.utils.BookingManager
import com.example.railora.utils.PassengerManager

class PassengerBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetPassengerBinding? = null
    private val binding get() = _binding!!
    private lateinit var passengerAdapter: PassengerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = BottomSheetPassengerBinding.inflate(
            inflater,
            container,
            false
        )

        setupPassengerRecyclerView()

        binding.btnReviewJourney.alpha = 0.5f
        binding.btnReviewJourney.isEnabled = false

        binding.tvAddPassenger.setOnClickListener {

            AddPassengerBottomSheet {

                passengerAdapter.notifyDataSetChanged()

            }.show(
                parentFragmentManager,
                "AddPassengerBottomSheet"
            )
        }

        binding.btnReviewJourney.setOnClickListener {

            BookingManager.selectedPassengers.clear()

            PassengerManager.passengerList
                .filter { it.isSelected }
                .forEach {

                    BookingManager.selectedPassengers.add(it)
                }

            dismiss()

            android.util.Log.d(
                "BOOKING",
                BookingManager.selectedPassengers.toString()
            )

            requireActivity()
                .findNavController(R.id.mainNavHost)
                .navigate(R.id.reviewJourneyFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        passengerAdapter.notifyDataSetChanged()
    }

    private fun setupPassengerRecyclerView() {

        passengerAdapter = PassengerAdapter(
            PassengerManager.passengerList
        ) {

            val hasSelectedPassenger =
                PassengerManager.passengerList.any {
                    it.isSelected
                }

            binding.btnReviewJourney.isEnabled =
                hasSelectedPassenger

            binding.btnReviewJourney.alpha =
                if (hasSelectedPassenger) 1f else 0.5f
        }

        binding.rvPassengers.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvPassengers.adapter =
            passengerAdapter
    }
}