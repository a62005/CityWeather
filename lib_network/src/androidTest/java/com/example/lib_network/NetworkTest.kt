package com.example.lib_network

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject
import kotlin.getValue

@RunWith(AndroidJUnit4::class)
@LargeTest
class NetworkTest {

    private val networkManager: NetworkManager by inject(NetworkManager::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun insert() {
        runBlocking {
            val countryResp = networkManager.countryApiService.getCountryInfo("TW")
            val data = countryResp.body()?.firstOrNull()
            println("Country Response: ${countryResp.isSuccessful}, Body: $data")

            data?.let {
                val weatherResp = networkManager.weatherApiService.getWeather(it.latitude, it.longitude)
                println("Weather Response: ${weatherResp.isSuccessful}, Body: ${weatherResp.body()}")
            } ?: run {
                println("No country data found for TW")
            }
        }
    }

    @After
    fun finish() {
        println("Done")
    }
}