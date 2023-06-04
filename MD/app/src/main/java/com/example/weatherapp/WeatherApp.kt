package com.example.weatherapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.ui.navigation.Screen
import com.example.weatherapp.ui.screen.detail.DetailScreen
import com.example.weatherapp.ui.screen.home.HomeScreen

@Composable
fun WeatherApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen(
                    navigateToDetail = { cityName ->
                        navController.navigate(Screen.Detail.createRoute(cityName))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("cityName"){ type = NavType.StringType })
            ){
                val cityName = it.arguments?.getString("cityName") ?: ""
                DetailScreen(
                    cityName = cityName,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}