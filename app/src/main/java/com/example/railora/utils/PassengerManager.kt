package com.example.railora.utils

import com.example.railora.models.Passenger

object PassengerManager {

    val passengerList = mutableListOf(

        Passenger(
            firstName = "Ahmed",
            lastName = "Hassan",
            age = "28",
            gender = "Male",
            berthPreference = "Lower"
        ),

        Passenger(
            firstName = "Anjali",
            lastName = "Sharma",
            age = "24",
            gender = "Female",
            berthPreference = "Upper"
        ),

        Passenger(
            firstName = "Arjun",
            lastName = "Mishra",
            age = "35",
            gender = "Male",
            berthPreference = "Middle"
        )
    )
}