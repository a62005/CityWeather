package com.example.cityweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_database.entities.CityBean
import com.example.cityweather.repo.SelectCityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectCityViewModel(private val repo: SelectCityRepository): ViewModel() {

    private val _citiesList = MutableStateFlow<List<CityBean>>(emptyList())
    val citiesList: StateFlow<List<CityBean>> = _citiesList.asStateFlow()

    init {
        viewModelScope.launch {
            repo.observeCity.collect {
                _citiesList.value = it
            }
        }
    }
}