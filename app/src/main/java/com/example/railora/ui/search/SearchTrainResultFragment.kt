package com.example.railora.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.railora.databinding.FragmentSearchTrainResultBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.railora.adapters.TrainResultAdapter
import com.example.railora.models.TrainClass
import com.example.railora.models.TrainResult
import android.widget.PopupMenu

class SearchTrainResultFragment : Fragment() {

    private var _binding: FragmentSearchTrainResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainResultAdapter: TrainResultAdapter
    private val trainList = mutableListOf<TrainResult>()
    private val originalTrainList = mutableListOf<TrainResult>()
    private var fromStation: String? = null
    private var toStation: String? = null
    private var journeyDate: String? = null
    private var fromStationCode: String? = null
    private var toStationCode: String? = null

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

        binding.tvBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        fromStationCode =
            arguments?.getString("from_station_code")

        toStationCode =
            arguments?.getString("to_station_code")

        journeyDate =
            arguments?.getString("journey_date")

        binding.tvJourneyRoute.text =
            "$fromStationCode To $toStationCode"

        setupRecyclerView()

        binding.layoutSortBy.setOnClickListener {
            showSortPopup()
        }

        binding.layoutClassFilter.setOnClickListener {
            showClassPopup()
        }

        binding.layoutQuota.setOnClickListener {
            showQuotaPopup()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getDummyTrainList(): List<TrainResult> {

        return listOf(

            TrainResult(
                trainNumber = "20631",
                trainName = "Vande Bharat Express",
                departureTime = "23:58",
                arrivalTime = "06:34",
                duration = "06h 36m",
                source = "SRR",
                destination = "TVC",
                classes = listOf(
                    TrainClass(
                        classCode = "SL",
                        fare = "₹325",
                        availability = "WL 83"
                    ),
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
            ),

            TrainResult(
                trainNumber = "12678",
                trainName = "Intercity Express",
                departureTime = "11:20",
                arrivalTime = "19:10",
                duration = "07h 50m",
                source = "",
                destination = "",
                classes = listOf(
                    TrainClass("SL", "₹340", "AVL 56"),
                    TrainClass("3A", "₹890", "AVL 18"),
                    TrainClass("2A", "₹1450", "AVL 06")
                )
            ),

            TrainResult(
                trainNumber = "22645",
                trainName = "Superfast Express",
                departureTime = "15:45",
                arrivalTime = "23:10",
                duration = "07h 25m",
                source = "",
                destination = "",
                classes = listOf(
                    TrainClass("SL", "₹380", "AVL 42"),
                    TrainClass("3A", "₹980", "AVL 11"),
                    TrainClass("2A", "₹1650", "WL 03"),
                    TrainClass("1A", "₹2430", "AVL 11"),
                    TrainClass("EC", "1130", "WL 37"),
                    TrainClass("CC", "₹450", "WL 20"),

                )
            ),

            TrainResult(
                trainNumber = "16382",
                trainName = "Night Express",
                departureTime = "22:15",
                arrivalTime = "05:40",
                duration = "07h 25m",
                source = "",
                destination = "",
                classes = listOf(
                    TrainClass("SL", "₹300", "AVL 70"),
                    TrainClass("3A", "₹850", "AVL 21"),
                    TrainClass("2A", "₹1350", "AVL 09")
                )
            )

        )
    }

    private fun setupRecyclerView() {

        originalTrainList.clear()
        originalTrainList.addAll(getDummyTrainList())

        trainList.clear()
        trainList.addAll(originalTrainList)

        trainResultAdapter = TrainResultAdapter(
            trainList = trainList,
            fromStation = fromStationCode ?: "",
            toStation = toStationCode ?: "",
            onClassClick = { train, trainClass ->

                val wasSelected = trainClass.isSelected

                trainList.forEach { currentTrain ->
                    currentTrain.classes.forEach {
                        it.isSelected = false
                    }
                }

                if (!wasSelected) {
                    trainClass.isSelected = true
                }

                trainResultAdapter.notifyDataSetChanged()

                if (!wasSelected) {

                    TrainDetailsBottomSheet(
                        trainName = "${train.trainName} (${train.trainNumber})",
                        selectedClass = trainClass.classCode,
                        fare = trainClass.fare,
                        journeyDate = journeyDate ?: ""
                    ).show(
                        childFragmentManager,
                        "TrainDetailsBottomSheet"
                    )
                }
            }
        )

        binding.rvTrainResults.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvTrainResults.adapter = trainResultAdapter
    }

    private fun showSortPopup() {

        val popupMenu = PopupMenu(
            requireContext(),
            binding.layoutSortBy
        )

        popupMenu.menu.add("Departure Time")
        popupMenu.menu.add("Arrival Time")
        popupMenu.menu.add("Duration")
        popupMenu.menu.add("Train Name")

        popupMenu.setOnMenuItemClickListener { menuItem ->

            binding.tvSortBy.text = menuItem.title

            when(menuItem.title.toString()) {

                "Departure Time" -> {
                    trainList.sortBy {
                        it.departureTime
                    }
                }

                "Arrival Time" -> {
                    trainList.sortBy {
                        it.arrivalTime
                    }
                }

                "Train Name" -> {
                    trainList.sortBy {
                        it.trainName
                    }
                }
            }

            trainResultAdapter.notifyDataSetChanged()

            true
        }

        popupMenu.show()
    }

    private fun showClassPopup() {

        val popupMenu = PopupMenu(
            requireContext(),
            binding.layoutClassFilter
        )

        popupMenu.menu.add("All")
        popupMenu.menu.add("SL")
        popupMenu.menu.add("3A")
        popupMenu.menu.add("2A")
        popupMenu.menu.add("CC")
        popupMenu.menu.add("EC")
        popupMenu.menu.add("1A")

        popupMenu.setOnMenuItemClickListener { menuItem ->

            val selectedClass =
                menuItem.title.toString()

            binding.tvClassFilter.text =
                selectedClass

            applyClassFilter(selectedClass)

            true
        }

        popupMenu.show()
    }

    private fun applyClassFilter(
        classCode: String
    ) {

        trainList.clear()

        if (classCode == "All") {

            trainList.addAll(
                originalTrainList
            )

        } else {

            trainList.addAll(

                originalTrainList.filter { train ->

                    train.classes.any {

                        it.classCode == classCode
                    }
                }
            )
        }

        trainResultAdapter.notifyDataSetChanged()
    }

    private fun showQuotaPopup() {

        val popupMenu = PopupMenu(
            requireContext(),
            binding.layoutQuota
        )

        popupMenu.menu.add("General")
        popupMenu.menu.add("Tatkal")
        popupMenu.menu.add("Premium Tatkal")

        popupMenu.setOnMenuItemClickListener { menuItem ->

            binding.tvQuota.text =
                menuItem.title

            true
        }

        popupMenu.show()
    }
}