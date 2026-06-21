package com.example.railora.utils

import com.example.railora.models.Passenger

object PassengerManager {

    val passengerList = mutableListOf(
        Passenger(
            firstName = "Gowtham",
            lastName = "A",
            age = "19",
            gender = "Male",
            berthPreference = "Lower"
        )
    )
}