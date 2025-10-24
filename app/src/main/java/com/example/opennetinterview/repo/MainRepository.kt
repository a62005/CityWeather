package com.example.opennetinterview.repo

import android.util.Log
import com.example.lib_database.dao.CityDao
import com.example.lib_database.dao.WeatherDao
import com.example.lib_database.entities.CityBean
import com.example.lib_database.entities.WeatherBean
import com.example.lib_database.entities.WeekWeatherBean
import com.example.lib_network.NetworkManager
import com.example.opennetinterview.utils.TimeUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.Locale

class MainRepository(
    private val ioScope: CoroutineScope,
    private val networkManager: NetworkManager,
    private val cityDao: CityDao
) {

    init {
        val defaultCountry = Locale.getDefault().country
        setWeatherData(defaultCountry)
    }

    private val _observeCity = MutableSharedFlow<CityBean>()
    val observeCity: Flow<CityBean> = _observeCity

    private val _observeTodayWeather = MutableSharedFlow<WeatherBean>()
    val observeTodayWeather: Flow<WeatherBean> = _observeTodayWeather

    private val _observeWeekWeather = MutableSharedFlow<List<WeekWeatherBean>>()
    val observeWeekWeather: Flow<List<WeekWeatherBean>> = _observeWeekWeather

    fun setWeatherData(country: String) {
        ioScope.launch {
            cityDao.getCityByCountry(country)?.let { cityBean ->
                networkManager.weatherApiService.getForecastWeather("${cityBean.latitude},${cityBean.longitude}").let { response ->
                    if (response.isSuccessful) {
                        response.body()?.let { weatherData ->
                            val todayWeatherInfo = weatherData.forecast.forecastDays.first()
                            val todayWeather = WeatherBean(
                                country = cityBean.country,
                                city = cityBean.city,
                                date = TimeUtils.formatToMonthDay(todayWeatherInfo.date),
                                weekday = TimeUtils.formatToWeekday(todayWeatherInfo.date),
                                timeOfDay = weatherData.currentWeather.lastUpdatedTime.split(" ").last(),
                                weather = weatherData.currentWeather.currentCondition.weather,
                                weatherUrl = weatherData.currentWeather.currentCondition.weatherUrl,
                                changeOfRain = todayWeatherInfo.dayInfo.changeOfRain,
                                temp = weatherData.currentWeather.temp.toInt(),
                                tempMax = todayWeatherInfo.dayInfo.tempMax.toInt(),
                                tempMin = todayWeatherInfo.dayInfo.tempMin.toInt(),
                                humidity = weatherData.currentWeather.humidity,
                                windKph = weatherData.currentWeather.windKph.toInt()
                            )
                            val forecastWeatherInfo = weatherData.forecast.forecastDays.drop(1).map {
                                WeekWeatherBean(
                                    weekday = TimeUtils.formatToWeekday(it.date),
                                    weatherUrl = it.dayInfo.condition.weatherUrl,
                                    changeOfRain = it.dayInfo.changeOfRain,
                                    tempMin = it.dayInfo.tempMin.toInt(),
                                    tempMax = it.dayInfo.tempMax.toInt()
                                )
                            }
                            Log.d("abcd", "setWeatherData: $cityBean")
                            _observeCity.emit(cityBean)
                            _observeTodayWeather.emit(todayWeather)
                            _observeWeekWeather.emit(forecastWeatherInfo)
                        }
                    }
                }
            }
        }
    }
}