package com.example.railora.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railora.databinding.BottomSheetAddPassengerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.railora.models.Passenger
import com.example.railora.utils.PassengerManager
class AddPassengerBottomSheet(
    private val onPassengerAdded: (() -> Unit)? = null
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetAddPassengerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = BottomSheetAddPassengerBinding.inflate(
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
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddPassenger.setOnClickListener {
            val firstName =
                binding.etFirstName.text.toString().trim()

            val lastName =
                binding.etLastName.text.toString().trim()

            val age =
                binding.etAge.text.toString().trim()

            val gender =
                binding.etGender.text.toString().trim()

            val berth =
                binding.etBerthPreference.text.toString().trim()

            if (firstName.isEmpty()) {
                binding.etFirstName.error = "Required"
                return@setOnClickListener
            }

            if (lastName.isEmpty()) {
                binding.etLastName.error = "Required"
                return@setOnClickListener
            }

            if (age.isEmpty()) {
                binding.etAge.error = "Required"
                return@setOnClickListener
            }

            if (gender.isEmpty()) {
                binding.etGender.error = "Required"
                return@setOnClickListener
            }

            if (berth.isEmpty()) {
                binding.etBerthPreference.error = "Required"
                return@setOnClickListener
            }

            PassengerManager.passengerList.add(
                Passenger(
                    firstName = firstName,
                    lastName = lastName,
                    age = age,
                    gender = gender,
                    berthPreference = berth
                )
            )
            onPassengerAdded?.invoke()

            android.util.Log.d(
                "PASSENGER",
                PassengerManager.passengerList.toString()
            )
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}