package com.example.cityweather.repo

import com.example.lib_database.dao.CityDao
import com.example.lib_database.entities.CityBean
import kotlinx.coroutines.flow.Flow

class SelectCityRepository(
    private val cityDao: CityDao
) {
    val observeCity: Flow<List<CityBean>>
        get() = cityDao.observeCity()
}