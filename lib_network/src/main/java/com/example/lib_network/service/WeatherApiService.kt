package com.example.lib_network.service

import com.example.lib_network.Config
import com.example.lib_network.datamodel.WeatherDataModel
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApiService {
    
    @POST("data/2.5/weather?appid=${Config.WEATHER_API_KEY}")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Response<WeatherDataModel>
}