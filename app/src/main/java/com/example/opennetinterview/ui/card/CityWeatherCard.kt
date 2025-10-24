package com.example.opennetinterview.ui.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lib_database.entities.CityBean
import com.example.lib_database.entities.WeatherBean
import com.example.lib_database.entities.WeekWeatherBean
import com.example.opennetinterview.ui.item.CityItem
import com.example.opennetinterview.ui.item.WeatherItem
import com.example.opennetinterview.ui.item.WeekWeatherItem
import com.example.opennetinterview.ui.theme.OpenNetInterviewTheme

@Composable
fun CityWeatherCard(
    cityBean: CityBean, 
    weatherBean: WeatherBean, 
    weekWeatherList: List<WeekWeatherBean>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(true) }
    
    Column(
        modifier = modifier
            .border(
                width = 2.dp, // 邊框粗細
                color = Color.Gray, // 邊框顏色
                shape = RoundedCornerShape(8.dp) // 圓角形狀
            )
    ) {
        CityItem(
            country = cityBean.country, 
            city = cityBean.city,
            onClick = { isExpanded = !isExpanded }
        )
        
        // 使用 AnimatedVisibility 來控制展開/收起動畫
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                WeatherItem(weatherBean)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    weekWeatherList.forEach { weekWeather ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            WeekWeatherItem(bean = weekWeather)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherItemPreview() {
    OpenNetInterviewTheme {
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
    }
}