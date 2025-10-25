package com.example.cityweather.repo

import android.util.Log
import com.example.lib_database.dao.CityDao
import com.example.lib_database.entities.CityBean
import com.example.lib_network.NetworkManager
import com.example.lib_network.datamodel.CountryDataModel
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
        val isoCountries = Locale.getISOCountries().toList()
        val response = networkManager.countryApiService.getAllCountriesInfo()
        if (response.isSuccessful) {
            response.body()?.let { countries ->
                val countriesMap = countries.associateBy { it.countryCode }
                isoCountries.forEach { countryCode ->
                    countriesMap[countryCode]?.let { countryData ->
                        saveCityBean(countryData)
                    }
                }
            }
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
                saveCityBean(countryData)
            }
        }
    }

    private fun saveCityBean(dataModel: CountryDataModel) {
        ioScope.launch {
            if (!dataModel.capital.isNullOrEmpty() && dataModel.capitalInfo != null && dataModel.latitude != null && dataModel.longitude != null && dataModel.city != null) {
                CityBean(
                    id = (dataModel.countryCode[0].code shl 8) or dataModel.countryCode[1].code,
                    countryCode = dataModel.countryCode,
                    country = dataModel.country,
                    city = dataModel.city!!,
                    latitude = dataModel.latitude!!,
                    longitude = dataModel.longitude!!
                ).apply {
                    cityDao.insert(this)
                    Log.d(TAG, "Successfully inserted data for $country")
                }
            }
        }
    }

}