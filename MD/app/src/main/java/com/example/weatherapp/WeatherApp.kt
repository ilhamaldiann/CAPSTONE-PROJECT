package com.example.weatherapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.ui.screen.Screen
import com.example.weatherapp.ui.screen.detail.DetailScreen
import com.example.weatherapp.ui.screen.detail.DetailViewModel
import com.example.weatherapp.ui.screen.home.HomeScreen
import com.example.weatherapp.ui.screen.home.HomeViewModel

@Composable
fun WeatherApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel
) {
    Scaffold(
        topBar = {},
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = homeViewModel,
                    navigateToDetail = { cityName ->
                        navController.navigate(Screen.Detail.createRoute(cityName))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("cityName") { type = NavType.StringType }),
            ) {
                val city = it.arguments?.getString("cityName") ?: "Jakarta"
                DetailScreen(
                    cityName = city,
                    viewModel = detailViewModel,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}