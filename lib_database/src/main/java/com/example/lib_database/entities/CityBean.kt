package com.example.lib_database.entities

import androidx.room.Entity

@Entity(primaryKeys = ["countryCode", "city"])
data class CityBean(
    val id: Int,
    val countryCode: String,
    val country: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)
