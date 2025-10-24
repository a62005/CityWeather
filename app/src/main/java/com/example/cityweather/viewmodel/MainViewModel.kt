package com.example.cityweather.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lib_database.entities.CityBean
import com.example.lib_database.entities.WeatherBean
import com.example.lib_database.entities.WeekWeatherBean
import com.example.cityweather.repo.MainRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val repo: MainRepository): ViewModel() {

    val city: Flow<CityBean> = repo.observeCity
    val todayWeather: Flow<WeatherBean> = repo.observeTodayWeather  
    val weekWeather: Flow<List<WeekWeatherBean>> = repo.observeWeekWeather

    fun setWeatherData(countryCode: String) {
        repo.setWeatherData(countryCode)
    }
}