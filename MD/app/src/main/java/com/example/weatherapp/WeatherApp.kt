package com.example.weatherapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.navigation.Screen
import com.example.weatherapp.ui.screen.detail.DetailScreen
import com.example.weatherapp.ui.screen.onBoarding.OnBoardingScreen
import com.example.weatherapp.ui.screen.onBoarding.OnBoardingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun WeatherApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val startDestination by onBoardingViewModel.startDestination
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.OnBoarding.route) {
                OnBoardingScreen(navController = navController)
            }
            composable(route = Screen.Detail.route) {
                DetailScreen()
            }
        }
    }
}