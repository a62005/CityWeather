package com.example.opennetinterview.ui.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opennetinterview.ui.theme.OpenNetInterviewTheme

@Composable
fun CityItem(
    country: String,
    city: String,
) {
    Text(
        text = "$country - $city",
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxWidth()                 // 讓文字佔滿可用寬度
            .padding(top = 6.dp, bottom = 6.dp, start = 6.dp, end = 0.dp),
        textAlign = TextAlign.Start,        // 靠左對齊
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true)
@Composable
fun CityItemPreview() {
    OpenNetInterviewTheme {
        CityItem(
            "Taiwan",
            "Taipei"
        )
    }
}