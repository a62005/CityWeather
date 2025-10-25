package com.example.cityweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class NavigationManager<T>(private val initialScreen: T) {
    var currentScreen by mutableStateOf(initialScreen)
        private set
    
    var navigationStack by mutableStateOf(listOf(initialScreen))
        private set
    
    var isBackNavigation by mutableStateOf(false)
        private set
    
    fun navigateTo(screen: T) {
        isBackNavigation = false
        currentScreen = screen
        navigationStack = navigationStack + screen
    }
    
    fun navigateBack(): Boolean {
        return if (navigationStack.size > 1) {
            isBackNavigation = true
            navigationStack = navigationStack.dropLast(1)
            currentScreen = navigationStack.last()
            true
        } else {
            false
        }
    }
    
    fun canGoBack(): Boolean = navigationStack.size > 1
    
    fun clearStack() {
        navigationStack = listOf(initialScreen)
        currentScreen = initialScreen
        isBackNavigation = false
    }
}

@Composable
fun <T> rememberNavigationManager(initialScreen: T): NavigationManager<T> {
    return rememberSaveable { NavigationManager(initialScreen) }
}