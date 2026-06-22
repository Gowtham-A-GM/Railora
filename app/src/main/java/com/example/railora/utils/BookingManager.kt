package com.example.railora.utils

import com.example.railora.models.Passenger

object BookingManager {

    var trainName = ""
    var trainNumber = ""

    var departureTime = ""
    var arrivalTime = ""

    var fromStation = ""
    var toStation = ""

    var journeyDate = ""

    var selectedClass = ""
    var fare = ""

    val selectedPassengers =
        mutableListOf<Passenger>()
}