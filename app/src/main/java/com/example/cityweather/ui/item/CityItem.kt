package com.example.cityweather.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cityweather.ui.theme.CityWeatherTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CityItem(
    country: String,
    city: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = city,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            modifier = Modifier
                .weight(1f),
            text = country,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityItemPreview() {
    CityWeatherTheme {
        CityItem(
            "Taiwan",
            "Taipei"
        )
    }
}