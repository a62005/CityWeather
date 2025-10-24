package com.example.lib_network.datamodel

import com.google.gson.annotations.SerializedName

data class WeatherDataModel(
    @SerializedName("location")
    val location: LocationInfo,
    @SerializedName("current")
    val currentWeather: CurrentWeather,
    @SerializedName("forecast")
    val forecast: ForecastInfo
 )

data class LocationInfo(
    val country: String
)

data class CurrentWeather(
    @SerializedName("last_updated")
    val lastUpdatedTime: String,
    @SerializedName("temp_c")
    val temp: Double,
    @SerializedName("condition")
    val currentCondition: ConditionInfo,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("wind_kph")
    val windKph: Double
)

data class ConditionInfo(
    @SerializedName("text")
    val weather: String,
    @SerializedName("icon")
    val weatherUrl: String
)

data class ForecastInfo(
    @SerializedName("forecastday")
    val forecastDays: List<ForecastDayInfo>
)

data class ForecastDayInfo(
    val date: String,
    @SerializedName("day")
    val dayInfo: DayInfo
)

data class DayInfo(
    @SerializedName("maxtemp_c")
    val tempMax: Double,
    @SerializedName("mintemp_c")
    val tempMin: Double,
    @SerializedName("condition")
    val condition: ConditionInfo,
    @SerializedName("daily_chance_of_rain")
    val changeOfRain: Int
)