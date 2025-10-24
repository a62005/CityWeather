package com.example.opennetinterview.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.opennetinterview.ui.screen.MainScreen
import com.example.opennetinterview.ui.screen.SelectCityScreen
import com.example.opennetinterview.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

enum class Screen {
    Main, SelectCity
}

@Composable
fun NavigationContainer(modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf(Screen.Main) }
    val mainViewModel: MainViewModel = koinViewModel()
    
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
                    }
                )
            }
        }
    }
}