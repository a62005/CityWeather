package com.example.opennetinterview.repo

import android.util.Log
import com.example.lib_database.dao.CityDao
import com.example.lib_database.entities.CityBean
import com.example.lib_network.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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
            if (cityDao.isEmpty()) {
                initDefaultCountry()
                initAllCountries()
            }
        }
    }

    private suspend fun initDefaultCountry() {
        val defaultCountry = Locale.getDefault().country
        setCountry(defaultCountry)
    }

    private suspend fun initAllCountries() {
        // TODO test with limited countries
        val countries = Locale.getISOCountries().take(20)
        countries.forEach { code ->
            setCountry(code)
        }
    }

    private suspend fun setCountry(countryCode: String) {
        if (cityDao.getCityByCountryCode(countryCode) != null) {
            return
        }
        val response = networkManager.countryApiService.getCountryInfo(countryCode)
        if (response.isSuccessful) {
            val countries = response.body()
            Log.d(TAG, "Response body size: ${countries?.size}")
            countries?.firstOrNull()?.let { countryData ->
                Log.d(TAG, "Country data: ${countryData.country}, ${countryData.city}")
                if (!countryData.capital.isNullOrEmpty() && countryData.capitalInfo != null) {
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
            }
        }
    }
}