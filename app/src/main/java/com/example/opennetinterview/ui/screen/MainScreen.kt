package com.example.opennetinterview.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lib_database.entities.CityBean
import com.example.lib_database.entities.WeatherBean
import com.example.lib_database.entities.WeekWeatherBean
import com.example.opennetinterview.R
import com.example.opennetinterview.ui.card.CityWeatherCard

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onSelectCityClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CityWeatherCard(
            CityBean(
                countryCode = "TW",
                country = "Taiwan",
                city = "Taipei",
                latitude = 25.0330,
                longitude = 121.5654
            ),
            WeatherBean(
                country = "Taiwan",
                city = "Taipei",
                timeInMillis = System.currentTimeMillis(),
                date = "10/24",
                weekOfDay = "Friday",
                timeOfDay = "3:00 PM",
                weather = "Sunny",
                weatherUrl = "https://cdn.weatherapi.com/weather/64x64/day/308.png",
                changeOfRain = 10,
                temp = 30,
                tempMin = 25,
                tempMax = 32,
                humidity = 60,
                windKph = 18
            ),
            weekWeatherList = List(7) {
                WeekWeatherBean(
                    weekOfDay = "Fri",
                    weatherUrl = "https://cdn.weatherapi.com/weather/64x64/day/308.png",
                    changeOfRain = 10,
                    tempMin = 25,
                    tempMax = 32
                )
            }
        )

        Button(
            onClick = onSelectCityClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(
                R.string.btn_select_other_city
            ))
        }
    }
}