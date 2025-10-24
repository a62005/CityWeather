package com.example.lib_network.service

import com.example.lib_network.Config
import com.example.lib_network.datamodel.WeatherDataModel
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApiService {
    
    @POST("forecast.json?alerts=no&key=${Config.WEATHER_API_KEY}")
    suspend fun getForecastWeather(
        @Query("q") query: String, // q=lat,lon or city name
        @Query("days") days: Int = 7
    ): Response<WeatherDataModel>
}