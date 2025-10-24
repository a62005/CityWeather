package com.example.lib_database.entities

import androidx.room.Entity

@Entity(primaryKeys = ["country", "city", "timeInMillis"])
data class WeatherBean(
    val country: String,
    val city: String,
    val timeInMillis: Long,
    val date: String,
    val weather: String,
    val weatherUrl: String,
    val changeOfRain: Int,
    val temp: Int,
    val tempMin: Int,
    val tempMax: Int,
    val humidity: Int,
    val windKph: Int
)
