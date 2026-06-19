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

class PassengerBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetPassengerBinding? = null
    private val binding get() = _binding!!

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

        binding.tvAddPassenger.setOnClickListener {

            android.widget.Toast.makeText(
                requireContext(),
                "Clicked",
                android.widget.Toast.LENGTH_SHORT
            ).show()

            AddPassengerBottomSheet().show(
                parentFragmentManager,
                "AddPassengerBottomSheet"
            )
        }

        binding.btnReviewJourney.setOnClickListener {

            dismiss()

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
}