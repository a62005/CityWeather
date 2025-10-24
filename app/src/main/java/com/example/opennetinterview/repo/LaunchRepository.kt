package com.example.opennetinterview.repo

import android.util.Log
import com.example.lib_database.dao.CityDao
import com.example.lib_database.dao.WeatherDao
import com.example.lib_database.entities.CityBean
import com.example.lib_database.entities.WeatherBean
import com.example.lib_network.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlin.collections.firstOrNull

class LaunchRepository(
    private val ioScope: CoroutineScope,
    private val networkManager: NetworkManager,
    private val cityDao: CityDao,
    private val weatherDao: WeatherDao
) {

    companion object {
        private const val TAG = "LaunchRepository"
    }

    fun init() {
        ioScope.launch {
            initDefaultCountryAndWeather()
            if (cityDao.isEmpty()) {
                setAllCountries()
            }
        }
    }

    private suspend fun initDefaultCountryAndWeather() {
        val defaultCountry = Locale.getDefault().country
        (cityDao.getCityByCountry(defaultCountry) ?: getCountry(defaultCountry))?.let { cityBean ->
            getWeather(cityBean.country, cityBean.city, cityBean.latitude, cityBean.longitude)
        }
    }

    private suspend fun setAllCountries() {
        // TODO : Limit to 10 countries for testing purposes
        val countries = Locale.getISOCountries().take(3)
        countries.forEach { code ->
            getCountry(code)
        }
    }

    private suspend fun getCountry(country: String): CityBean? = withContext(ioScope.coroutineContext) {
        val response = networkManager.countryApiService.getCountryInfo(country)
        Log.d(TAG, "Get Country Response Successful: ${response.isSuccessful} ${response.body()}")
        return@withContext if (response.isSuccessful) {
            response.body()?.firstOrNull()?.let { country ->
                CityBean(
                    countryCode = country.countryCode,
                    country = country.country,
                    city = country.city,
                    latitude = country.latitude,
                    longitude = country.longitude
                ).apply {
                    cityDao.insert(this)
                }
            }
        } else {
            null
        }
    }

    private fun getWeather(country: String, city: String, latitude: Double, longitude: Double) {
        ioScope.launch {
            val response = networkManager.weatherApiService.getWeather(latitude, longitude)
            Log.d(TAG, "Get Weather Response Successful: ${response.isSuccessful} ${response.body()}")
            if (response.isSuccessful) {
                response.body()?.let { weather ->
                    WeatherBean(
                        country = country,
                        city = city,
                        weather = weather.weatherMain,
                        temp = weather.temp,
                        tempMin = weather.tempMin,
                        tempMax = weather.tempMax,
                        pressure = weather.pressure,
                        humidity = weather.humidity
                    ).apply {
                        weatherDao.insert(this)
                    }
                }
            }
        }
    }
}