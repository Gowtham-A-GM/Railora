package com.example.railora.models

data class Passenger(
    val firstName: String,
    val lastName: String,
    val age: String,
    val gender: String,
    val berthPreference: String,
    var isSelected: Boolean = false
)