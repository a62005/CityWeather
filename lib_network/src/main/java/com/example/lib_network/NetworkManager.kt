package com.example.lib_network

import com.example.lib_network.service.CountryApiService
import com.example.lib_network.service.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkManager {
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    private val countryRetrofit = Retrofit.Builder()
        .baseUrl(Config.COUNTRY_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    private val weatherRetrofit = Retrofit.Builder()
        .baseUrl(Config.WEATHER_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val countryApiService: CountryApiService = countryRetrofit.create(CountryApiService::class.java)
    val weatherApiService: WeatherApiService = weatherRetrofit.create(WeatherApiService::class.java)
}