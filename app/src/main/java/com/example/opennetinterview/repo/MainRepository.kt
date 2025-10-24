package com.example.opennetinterview.repo

import com.example.lib_database.dao.CityDao
import com.example.lib_database.dao.WeatherDao
import com.example.lib_network.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainRepository(
    private val ioScope: CoroutineScope,
    private val networkManager: NetworkManager,
    private val cityDao: CityDao,
    private val weatherDao: WeatherDao
) {

    fun clearData() {
        ioScope.launch {
            weatherDao.deleteAll()
        }
    }
}