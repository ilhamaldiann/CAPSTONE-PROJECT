package com.example.weatherapp.ui.screen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{cityName}") {
        fun createRoute(cityName: String) = "home/$cityName"
    }
}
