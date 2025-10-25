package com.example.lib_database.entities

data class WeatherBean(
    val id: Int,
    val currentWeather: CurrentWeatherBean,
    val weekWeather: List<WeekWeatherBean>
)

data class CurrentWeatherBean(
    val id: Int,
    val date: String,
    val weekday: String,
    val timeOfDay: String?,
    val weather: String,
    val weatherUrl: String,
    val changeOfRain: Int,
    val temp: Int,
    val tempMin: Int,
    val tempMax: Int,
    val humidity: Int,
    val windKph: Int
)

data class WeekWeatherBean(
    val id: Int,
    val weekday: String,
    val weatherUrl: String,
    val changeOfRain: Int,
    val tempMin: Int,
    val tempMax: Int
)
