package com.example.cityweather.repo

import com.example.lib_database.dao.CityDao
import com.example.lib_database.entities.CityBean
import com.example.lib_database.entities.WeatherBean
import com.example.lib_database.entities.WeekWeatherBean
import com.example.lib_network.NetworkManager
import com.example.cityweather.utils.TimeUtils
import com.example.lib_database.entities.CurrentWeatherBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.Locale

class MainRepository(
    private val ioScope: CoroutineScope,
    private val networkManager: NetworkManager,
    private val cityDao: CityDao
) {

    private var initJob: Job? = null

    init {
        initJob = ioScope.launch {
            val defaultCountry = Locale.getDefault().country
            cityDao.observeCityByCountryCode(defaultCountry).collect { cityBean ->
                cityBean?.let {
                    setWeatherData(it.countryCode)
                    initJob?.cancel()
                    initJob = null
                }
            }
        }
    }

    private val _observeCity = MutableSharedFlow<CityBean>(replay = 1, extraBufferCapacity = 1)
    val observeCity: Flow<CityBean> = _observeCity

    private val _observeWeather = MutableSharedFlow<WeatherBean>(replay = 1, extraBufferCapacity = 1)
    val observeWeather: Flow<WeatherBean> = _observeWeather

    fun setWeatherData(countryCode: String) {
        ioScope.launch {
            cityDao.getCityByCountryCode(countryCode)?.let { cityBean ->
                networkManager.weatherApiService.getForecastWeather("${cityBean.latitude},${cityBean.longitude}")
                    .let { response ->
                        if (response.isSuccessful) {
                            response.body()?.let { weatherData ->
                                val todayWeatherInfo = weatherData.forecast.forecastDays.first()
                                val todayWeather = CurrentWeatherBean(
                                    id = cityBean.id,
                                    date = TimeUtils.formatToMonthDay(todayWeatherInfo.date),
                                    weekday = TimeUtils.formatToWeekday(todayWeatherInfo.date),
                                    timeOfDay = weatherData.currentWeather.lastUpdatedTime.split(" ")
                                        .last(),
                                    weather = weatherData.currentWeather.currentCondition.weather,
                                    weatherUrl = "http://${weatherData.currentWeather.currentCondition.weatherUrl}",
                                    changeOfRain = todayWeatherInfo.dayInfo.changeOfRain,
                                    temp = weatherData.currentWeather.temp.toInt(),
                                    tempMax = todayWeatherInfo.dayInfo.tempMax.toInt(),
                                    tempMin = todayWeatherInfo.dayInfo.tempMin.toInt(),
                                    humidity = weatherData.currentWeather.humidity,
                                    windKph = weatherData.currentWeather.windKph.toInt()
                                )
                                val forecastWeatherInfo =
                                    weatherData.forecast.forecastDays.drop(1).map {
                                        WeekWeatherBean(
                                            id = cityBean.id,
                                            weekday = TimeUtils.formatToWeekday(it.date),
                                            weatherUrl = "http://${it.dayInfo.condition.weatherUrl}",
                                            changeOfRain = it.dayInfo.changeOfRain,
                                            tempMin = it.dayInfo.tempMin.toInt(),
                                            tempMax = it.dayInfo.tempMax.toInt()
                                        )
                                    }
                                val weatherBean = WeatherBean(
                                    id = cityBean.id,
                                    currentWeather = todayWeather,
                                    weekWeather = forecastWeatherInfo
                                )
                                _observeCity.emit(cityBean)
                                _observeWeather.emit(weatherBean)
                            }
                        }
                    }
            }
        }
    }
}