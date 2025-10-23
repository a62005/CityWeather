package com.example.lib_network.service

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApiService {
    
    @POST("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Response<String>
}