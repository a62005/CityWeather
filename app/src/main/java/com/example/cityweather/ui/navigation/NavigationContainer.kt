package com.example.cityweather.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.cityweather.ui.screen.MainScreen
import com.example.cityweather.ui.screen.SelectCityScreen
import com.example.cityweather.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

enum class Screen {
    Main, SelectCity
}

@Composable
fun NavigationContainer(modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf(Screen.Main) }
    val mainViewModel: MainViewModel = koinViewModel()

    val currentCity by mainViewModel.city.collectAsState()
    
    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            if (targetState == Screen.SelectCity) {
                slideInHorizontally(initialOffsetX = { it }) togetherWith
                slideOutHorizontally(targetOffsetX = { -it })
            } else {
                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
            }
        },
        modifier = modifier,
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            Screen.Main -> {
                MainScreen(
                    mainViewModel = mainViewModel,
                    onSelectCityClick = {
                        currentScreen = Screen.SelectCity
                    }
                )
            }
            Screen.SelectCity -> {
                SelectCityScreen(
                    onBackClick = {
                        currentScreen = Screen.Main
                    },
                    onCityClick = { city ->
                        mainViewModel.setWeatherData(city.countryCode)
                        currentScreen = Screen.Main
                    },
                    currentCountryCode = currentCity?.countryCode
                )
            }
        }
    }
}