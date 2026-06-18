package com.example.railora.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.railora.adapters.StationAdapter
import com.example.railora.databinding.FragmentStationSearchBinding
import com.example.railora.models.Station
import android.text.Editable
import android.text.TextWatcher

class StationSearchFragment : Fragment() {

    private var _binding: FragmentStationSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var stationAdapter: StationAdapter

    private val stationList = listOf(
        Station("CHENNAI CENTRAL", "MAS"),
        Station("CHENNAI EGMORE", "MS"),
        Station("TAMBARAM", "TBM"),
        Station("MAMBALAM", "MBM"),
        Station("MADURAI JN", "MDU"),
        Station("COIMBATORE JN", "CBE"),
        Station("TIRUCHIRAPPALLI", "TPJ"),
        Station("SALEM JN", "SA"),
        Station("ERNAKULAM JN", "ERS"),
        Station("SHORANUR JN", "SRR"),
        Station("BENGALURU CITY", "SBC"),
        Station("MYSURU JN", "MYS"),
        Station("HYDERABAD DECAN", "HYB"),
        Station("SECUNDERABAD JN", "SC"),
        Station("VIJAYAWADA JN", "BZA"),
        Station("VISAKHAPATNAM", "VSKP"),
        Station("HOWRAH JN", "HWH"),
        Station("SEALDAH", "SDAH"),
        Station("NEW DELHI", "NDLS"),
        Station("MUMBAI CENTRAL", "MMCT"),
        Station("DADAR", "DR"),
        Station("PUNE JN", "PUNE"),
        Station("AHMEDABAD JN", "ADI"),
        Station("JAIPUR", "JP"),
        Station("LUCKNOW NR", "LKO")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStationSearchBinding.inflate(
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

        binding.etSearchStation.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    val query =
                        s.toString()
                            .trim()
                            .lowercase()

                    val filteredStations =
                        stationList.filter {

                            it.stationName.lowercase()
                                .contains(query)

                                    ||

                                    it.stationCode.lowercase()
                                        .contains(query)
                        }

                    stationAdapter.updateStations(
                        filteredStations
                    )
                }

                override fun afterTextChanged(
                    s: Editable?
                ) = Unit
            }
        )

        binding.rvStations.layoutManager =
            LinearLayoutManager(requireContext())

        stationAdapter =
            StationAdapter(stationList) { station ->

                val fieldType =
                    findNavController()
                        .previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<String>("field_type")

                findNavController()
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        if (fieldType == "FROM") "from_station"
                        else "to_station",
                        "${station.stationName} (${station.stationCode})"
                    )

                findNavController().navigateUp()
            }

        binding.rvStations.adapter = stationAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}