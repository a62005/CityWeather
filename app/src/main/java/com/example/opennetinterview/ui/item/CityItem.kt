package com.example.opennetinterview.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opennetinterview.ui.theme.OpenNetInterviewTheme

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
            .padding(top = 6.dp, bottom = 6.dp, start = 6.dp, end = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左邊：城市名稱
        Text(
            text = city,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium
        )
        
        // 右邊：國家名稱
        Text(
            text = country,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
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