package com.example.lib_network.service

import com.example.lib_network.datamodel.CountryDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {
    
    @GET("v3.1/alpha/{country}")
    suspend fun getCountryInfo(@Path("country") countryCode: String): Response<List<CountryDataModel>>
}