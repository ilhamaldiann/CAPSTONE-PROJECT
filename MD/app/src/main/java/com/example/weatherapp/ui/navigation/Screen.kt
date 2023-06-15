package com.example.weatherapp.ui.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object Detail : Screen("detail")
}