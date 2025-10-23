package com.example.lib_database.entities

import androidx.room.Entity

@Entity(primaryKeys = ["country", "city"])
data class CityBean(
    val country: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)
