package com.example.railora.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.railora.databinding.FragmentSearchTrainResultBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.railora.adapters.TrainResultAdapter
import com.example.railora.models.TrainClass
import com.example.railora.models.TrainResult

class SearchTrainResultFragment : Fragment() {

    private var _binding: FragmentSearchTrainResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchTrainResultBinding.inflate(
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

        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getDummyTrainList(): List<TrainResult> {

        return listOf(

            TrainResult(
                trainNumber = "20631",
                trainName = "MAQ TVC VB EXP",
                departureTime = "23:58",
                arrivalTime = "06:34",
                duration = "06h 36m",
                source = "SRR",
                destination = "TVC",
                classes = listOf(
                    TrainClass("SL", "₹325", "WL 83"),
                    TrainClass("CC", "₹450", "AVL 20"),
                    TrainClass("EC", "₹850", "WL 10"),
                    TrainClass("1A", "₹1500", "AVL 03")
                )
            ),

            TrainResult(
                trainNumber = "12623",
                trainName = "MAIL EXPRESS",
                departureTime = "18:20",
                arrivalTime = "06:30",
                duration = "12h 10m",
                source = "MAS",
                destination = "TVC",
                classes = listOf(
                    TrainClass("SL", "₹280", "AVL 45"),
                    TrainClass("3A", "₹980", "AVL 12"),
                    TrainClass("2A", "₹1450", "WL 04")
                )
            )

        )
    }

    private fun setupRecyclerView() {

        val adapter = TrainResultAdapter(
            trainList = getDummyTrainList(),
            onClassClick = { train, trainClass ->

                TrainDetailsBottomSheet(
                    trainName = "${train.trainName} (${train.trainNumber})",
                    selectedClass = trainClass.classCode,
                    fare = trainClass.fare
                ).show(
                    childFragmentManager,
                    "TrainDetailsBottomSheet"
                )
            }
        )

        binding.rvTrainResults.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvTrainResults.adapter = adapter
    }
}