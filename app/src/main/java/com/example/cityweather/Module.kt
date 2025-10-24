package com.example.cityweather

import com.example.cityweather.repo.LaunchRepository
import com.example.cityweather.repo.MainRepository
import com.example.cityweather.repo.SelectCityRepository
import com.example.cityweather.viewmodel.MainViewModel
import com.example.cityweather.viewmodel.SelectCityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::SelectCityViewModel)
}

private val repoModule = module {
    factoryOf(::LaunchRepository)
    factoryOf(::MainRepository)
    factoryOf(::SelectCityRepository)
}

val mainModule = listOf(module {
    factory { CoroutineScope(Dispatchers.IO) }
}, viewModelModule, repoModule)

