package com.example.opennetinterview.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opennetinterview.viewmodel.SelectCityViewModel
import com.example.lib_database.entities.CityBean
import com.example.opennetinterview.R
import com.example.opennetinterview.ui.item.CityItem
import com.example.opennetinterview.ui.theme.OpenNetInterviewTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectCityScreen(
    onBackClick: () -> Unit = {},
    onCityClick: (CityBean) -> Unit = {},
    viewModel: SelectCityViewModel = koinViewModel()
) {
    val citiesList by viewModel.citiesList.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onBackClick() }
            )
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Select City",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
            Box(modifier = Modifier.size(24.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (citiesList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(citiesList) { city ->
                    CityItem(
                        country = city.country,
                        city = city.city,
                        onClick = { onCityClick(city) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectCityScreenPreview() {
    OpenNetInterviewTheme {
        SelectCityScreen()
    }
}