package com.example.lib_database.entities

import androidx.room.Entity

@Entity(primaryKeys = ["country", "city"])
data class WeatherBean(
    val country: String,
    val city: String,
    val weather: String,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int
)
