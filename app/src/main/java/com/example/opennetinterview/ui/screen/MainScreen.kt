package com.example.opennetinterview.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.opennetinterview.R
import com.example.opennetinterview.ui.card.CityWeatherCard
import com.example.opennetinterview.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel(),
    onSelectCityClick: () -> Unit = {}
) {
    val city by mainViewModel.city.collectAsStateWithLifecycle(initialValue = null)
    val todayWeather by mainViewModel.todayWeather.collectAsStateWithLifecycle(initialValue = null)
    val weekWeather by mainViewModel.weekWeather.collectAsStateWithLifecycle(initialValue = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (city != null && todayWeather != null) {
            CityWeatherCard(
                cityBean = city!!,
                weatherBean = todayWeather!!,
                weekWeatherList = weekWeather
            )
        } else {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Button(
            onClick = onSelectCityClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_select_other_city))
        }
    }
}