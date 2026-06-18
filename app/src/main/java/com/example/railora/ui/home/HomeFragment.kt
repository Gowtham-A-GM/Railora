package com.example.railora.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.railora.R
import com.example.railora.databinding.FragmentHomeBinding
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var fromStationName: String? = null
    private var toStationName: String? = null
    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
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

        binding.layoutFromStation.setOnClickListener {

            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.set("field_type", "FROM")

            findNavController().navigate(
                R.id.action_homeFragment_to_stationSearchFragment
            )
        }

        binding.layoutToStation.setOnClickListener {

            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.set("field_type", "TO")

            findNavController().navigate(
                R.id.action_homeFragment_to_stationSearchFragment
            )
        }

        binding.layoutDate.setOnClickListener {

            val calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->

                    val selectedCalendar = Calendar.getInstance()

                    selectedCalendar.set(
                        year,
                        month,
                        dayOfMonth
                    )

                    val formattedDate =
                        SimpleDateFormat(
                            "dd MMM yyyy",
                            Locale.getDefault()
                        ).format(
                            selectedCalendar.time
                        )

                    selectedDate = formattedDate

                    binding.tvDate.text = formattedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.datePicker.minDate =
                System.currentTimeMillis()

            datePickerDialog.show()
        }

        binding.layoutTrackTrain.setOnClickListener {

            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    getString(R.string.track_train_url).toUri()
                )
            )
        }

        binding.layoutPnrStatus.setOnClickListener {

            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    getString(R.string.pnr_status_url).toUri()
                )
            )
        }

        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("from_station")
            ?.observe(viewLifecycleOwner) { station ->

                if (station == toStationName) {
                    Toast.makeText(
                        requireContext(),
                        "From and To stations cannot be same",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@observe
                }

                fromStationName = station
                binding.tvFromStation.text = station
            }

        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("to_station")
            ?.observe(viewLifecycleOwner) { station ->

                if (station == fromStationName) {

                    Toast.makeText(
                        requireContext(),
                        "From and To stations cannot be same",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@observe
                }

                toStationName = station
                binding.tvToStation.text = station
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}