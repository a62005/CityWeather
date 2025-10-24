package com.example.cityweather.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lib_database.entities.WeatherBean
import com.example.cityweather.R
import com.example.cityweather.ui.theme.CityWeatherTheme

@Composable
fun WeatherItem(bean: WeatherBean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberAsyncImagePainter(bean.weatherUrl),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Row(
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "${bean.temp}",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
            Text(
                text = "℃",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.chance_of_rain,
                        bean.changeOfRain
                    ),
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
                Text(
                    text = stringResource(
                        R.string.humidity,
                        bean.humidity
                    ),
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
                Text(
                    text = stringResource(
                        R.string.wind_speed,
                        bean.windKph
                    ),
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${bean.weekday} ${bean.date}",
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
                Text(
                    text = bean.weather,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherItemPreview() {
    CityWeatherTheme {
        WeatherItem(
            WeatherBean(
                country = "Taiwan",
                city = "Taipei",
                date = "10/24",
                weekday = "Friday",
                timeOfDay = "3:00 PM",
                weather = "Sunny",
                weatherUrl = "https://cdn.weatherapi.com/weather/64x64/day/308.png",
                changeOfRain = 10,
                temp = 30,
                tempMin = 25,
                tempMax = 32,
                humidity = 60,
                windKph = 18
            )
        )
    }
}