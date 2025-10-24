package com.example.opennetinterview.repo

import android.util.Log
import com.example.lib_database.dao.CityDao
import com.example.lib_database.entities.CityBean
import com.example.lib_network.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class LaunchRepository(
    private val ioScope: CoroutineScope,
    private val networkManager: NetworkManager,
    private val cityDao: CityDao
) {

    companion object {
        private const val TAG = "LaunchRepository"
    }

    fun init() {
        ioScope.launch {
            initDefaultCountry()
            initAllCountries()
        }
    }

    private suspend fun initDefaultCountry() {
        val defaultCountry = Locale.getDefault().country
        (cityDao.getCityByCountry(defaultCountry) ?: getCountry(defaultCountry))
    }

    private suspend fun initAllCountries() {
        val countries = Locale.getISOCountries()
        if (cityDao.getSize() != countries.size) {
            setAllCountries(countries.toList())
        }
    }

    private suspend fun setAllCountries(countries: List<String>) {
        // TODO : Limit to 10 countries for testing purposes
        val countries = countries.take(3)
        countries.forEach { code ->
            getCountry(code)
        }
    }

    private suspend fun getCountry(country: String): CityBean? =
        withContext(kotlinx.coroutines.Dispatchers.IO) {
            val response = networkManager.countryApiService.getCountryInfo(country)
            return@withContext if (response.isSuccessful) {
                val countries = response.body()
                Log.d(TAG, "Response body size: ${countries?.size}")
                countries?.firstOrNull()?.let { countryData ->
                    Log.d(TAG, "Country data: ${countryData.country}, ${countryData.city}")
                    CityBean(
                        countryCode = countryData.countryCode,
                        country = countryData.country,
                        city = countryData.city,
                        latitude = countryData.latitude,
                        longitude = countryData.longitude
                    ).apply {
                        cityDao.insert(this)
                        Log.d(TAG, "Successfully inserted data for $country")
                    }
                }
            } else {
                Log.e(TAG, "API call failed for $country: ${response.code()}")
                null
            }
        }
}