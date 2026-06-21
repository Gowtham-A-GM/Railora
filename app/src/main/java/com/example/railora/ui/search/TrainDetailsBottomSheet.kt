package com.example.railora.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.railora.adapters.AvailabilityAdapter
import com.example.railora.databinding.BottomSheetTrainDetailsBinding
import com.example.railora.models.AvailabilityDay
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TrainDetailsBottomSheet(
    private val trainName: String,
    private val selectedClass: String,
    private val fare: String,
    private val journeyDate: String
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetTrainDetailsBinding? = null
    private val binding get() = _binding!!

    private var isDateSelected = false
    private lateinit var availabilityList: List<AvailabilityDay>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = BottomSheetTrainDetailsBinding.inflate(
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

        setupUi()
        setupAvailabilityRecyclerView()
    }

    private fun setupUi() {

        binding.tvTrainName.text = trainName
        binding.btnContinue.alpha = 0.5f
        binding.btnContinue.isEnabled = false

        binding.tvSelectedClass.text =
            "$selectedClass • $fare"

        binding.btnContinue.setOnClickListener {

            dismiss()

            PassengerBottomSheet().show(
                parentFragmentManager,
                "PassengerBottomSheet"
            )
        }
    }

    private fun setupAvailabilityRecyclerView() {

        val availabilityList = mutableListOf<AvailabilityDay>()

        val dateFormat =
            java.text.SimpleDateFormat(
                "dd MMM yyyy",
                java.util.Locale.getDefault()
            )

        val displayDay =
            java.text.SimpleDateFormat(
                "dd",
                java.util.Locale.getDefault()
            )

        val displayWeek =
            java.text.SimpleDateFormat(
                "EEE",
                java.util.Locale.getDefault()
            )

        val calendar = java.util.Calendar.getInstance()

        calendar.time =
            dateFormat.parse(journeyDate)!!

        repeat(5) {

            availabilityList.add(

                AvailabilityDay(
                    displayDay.format(calendar.time),
                    displayWeek.format(calendar.time).uppercase(),
                    listOf(
                        "AVL 45",
                        "AVL 12",
                        "RAC 03",
                        "WL 08",
                        "AVL 67"
                    )[it]
                )
            )

            calendar.add(
                java.util.Calendar.DAY_OF_MONTH,
                1
            )
        }

        binding.rvAvailability.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        binding.rvAvailability.adapter =
            AvailabilityAdapter(availabilityList) { selectedDay ->

                availabilityList.forEach {
                    it.isSelected = false
                }

                selectedDay.isSelected = true

                binding.rvAvailability.adapter?.notifyDataSetChanged()

                binding.btnContinue.alpha = 1f
                binding.btnContinue.isEnabled = true
            }
    }

    override fun onStart() {
        super.onStart()

        val bottomSheet = dialog?.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        )

        bottomSheet?.setBackgroundResource(android.R.color.transparent)

        bottomSheet?.layoutParams?.height =
            ViewGroup.LayoutParams.WRAP_CONTENT
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}