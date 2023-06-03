package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.ViewModelFactory
import com.example.weatherapp.ui.screen.detail.DetailViewModel
import com.example.weatherapp.ui.screen.home.BookmarkedList
import com.example.weatherapp.ui.screen.home.HomeViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(application)
    }
    private val detailViewModel: DetailViewModel by viewModels {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BookmarkedList(viewModel = homeViewModel)
                }
            }
        }
    }
}