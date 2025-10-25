package com.example.cityweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_database.entities.CityBean
import com.example.lib_database.entities.WeatherBean
import com.example.cityweather.repo.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MainRepository): ViewModel() {

    private val _city = MutableStateFlow<CityBean?>(null)
    val city: StateFlow<CityBean?> get() = _city

    private val _weather = MutableStateFlow<WeatherBean?>(null)
    val weather: StateFlow<WeatherBean?> get() = _weather

    init {
        viewModelScope.launch {
            launch {
                repo.observeCity.collect {
                    _city.value = it
                }
            }
            launch {
                repo.observeWeather.collect {
                    _weather.value = it
                }
            }
        }
    }

    fun setWeatherData(countryCode: String) {
        _city.value = null
        _weather.value = null
        repo.setWeatherData(countryCode)
    }
}