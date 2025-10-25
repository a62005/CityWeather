package com.example.cityweather.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cityweather.ui.theme.CityWeatherTheme
import com.example.lib_database.entities.CityBean

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CityItem(
    cityBean: CityBean,
    onClick: () -> Unit = {}
) {
    Column (
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = cityBean.city,
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = cityBean.country,
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.LightGray,
            lineHeight = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityItemPreview() {
    CityWeatherTheme {
        CityItem(
            CityBean(
                id = 0,
                country = "United States",
                countryCode = "US",
                city = "New York",
                latitude = 40.7128,
                longitude = -74.0060
            )
        )
    }
}