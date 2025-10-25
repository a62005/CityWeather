package com.example.cityweather.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
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
    val navigationManager = rememberNavigationManager(Screen.Main)
    val mainViewModel: MainViewModel = koinViewModel()
    
    AnimatedContent(
        targetState = navigationManager.currentScreen,
        transitionSpec = {
            if (navigationManager.isBackNavigation) {
                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
            } else {
                slideInHorizontally(initialOffsetX = { it }) togetherWith
                slideOutHorizontally(targetOffsetX = { -it })
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
                        navigationManager.navigateTo(Screen.SelectCity)
                    }
                )
            }
            Screen.SelectCity -> {
                SelectCityScreen(
                    onBackClick = {
                        navigationManager.navigateBack()
                    },
                    onCityClick = { city ->
                        mainViewModel.setWeatherData(city.countryCode)
                        navigationManager.navigateBack()
                    }
                )
            }
        }
    }
}