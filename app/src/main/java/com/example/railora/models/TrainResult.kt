package com.example.railora.models

data class TrainResult(
    val trainNumber: String,
    val trainName: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val source: String,
    val destination: String,
    val classes: List<TrainClass>
)