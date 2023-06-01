package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.local.BookmarkEntity
import com.example.weatherapp.ui.screen.home.BookmarkedList
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.screen.home.HomeViewModel
import com.example.weatherapp.ui.ViewModelFactory

class MainActivity : ComponentActivity() {


    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = ArrayList<WeatherData>()
        viewModel.getBookmarkCity().observe(this) { listCity: List<BookmarkEntity> ->
            listCity.map {
                val item = WeatherData(it.cityName)
                items.add(item)
            }
            setContent {
                WeatherAppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        if (items.isNotEmpty()) {
                            BookmarkedList(viewModel = viewModel, list = items)
                        }
                    }
                }
            }
        }
    }
}