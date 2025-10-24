package com.example.opennetinterview.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lib_database.entities.WeekWeatherBean
import com.example.opennetinterview.ui.theme.OpenNetInterviewTheme

@Composable
fun WeekWeatherItem(bean: WeekWeatherBean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = bean.weekOfDay,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
        Image(
            painter = rememberAsyncImagePainter(bean.weatherUrl),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = "${bean.changeOfRain}%",
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
        Text(
            text = "${bean.tempMin} - ${bean.tempMax}",
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeekWeatherItemPreview() {
    OpenNetInterviewTheme {
        WeekWeatherItem(
            WeekWeatherBean(
                weekOfDay = "Mon",
                weatherUrl = "https://cdn.weatherapi.com/weather/64x64/day/308.png",
                changeOfRain = 45,
                tempMin = 18,
                tempMax = 25
            )
        )
    }
}