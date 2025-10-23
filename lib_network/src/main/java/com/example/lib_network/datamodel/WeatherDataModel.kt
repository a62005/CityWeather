package com.example.lib_network.datamodel

import com.google.gson.annotations.SerializedName

data class WeatherDataModel(
    @SerializedName("weather")
    val weather: List<WeatherInfo>,
    @SerializedName("main")
    val main: MainInfo,
    @SerializedName("sys")
    val sys: SysInfo
) {
    val weatherMain: String get() = weather.firstOrNull()?.main ?: ""
    val temp: Double get() = main.temp
    val tempMin: Double get() = main.temp_min
    val tempMax: Double get() = main.temp_max
    val pressure: Int get() = main.pressure
    val humidity: Int get() = main.humidity
    val country: String get() = sys.country
}

data class WeatherInfo(
    @SerializedName("main")
    val main: String
)

data class MainInfo(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)

data class SysInfo(
    @SerializedName("country")
    val country: String
)