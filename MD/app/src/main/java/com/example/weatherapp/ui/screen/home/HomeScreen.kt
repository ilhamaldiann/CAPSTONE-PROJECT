package com.example.weatherapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.local.entity.BookmarkEntity
import com.example.weatherapp.data.remote.response.CurrentWeatherResponse
import com.example.weatherapp.di.Injection
import com.example.weatherapp.ui.ViewModelFactory
import com.example.weatherapp.ui.components.WeatherCard
import com.example.weatherapp.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (String) -> Unit,
) {
    val items = ArrayList<WeatherData>()
    val bookmarkCity: List<BookmarkEntity> by viewModel.bookmarkCity

    LaunchedEffect(Unit){
        viewModel.getBookmarkCity()
    }

    if (bookmarkCity.isNotEmpty()) {
        Log.i("MainActivity", "onCreate: ${bookmarkCity[0].cityName} 1")
        items.clear()
        // clear ui state
        UiState.Success(emptyList<CurrentWeatherResponse>())
        bookmarkCity.map {
            val item = WeatherData(it.cityName)
            items.add(item)
        }
        Log.i("MainActivity", "onCreate: ${items.size}")
    }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Log.i("MainActivity", "onCreate: UiState.Loading 2")
                items.map {
                    viewModel.getWeatherData(it.city)
                }
            }

            is UiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(56.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.data) {
                        WeatherCard(
                            cityName = it.location.name,
                            status = it.current.condition.text,
                            tempC = it.current.tempC,
                            modifier = modifier.clickable {
                                navigateToDetail(it.location.name)
                            }
                        )
                    }
                }
                Log.i("MainActivity", "onCreate: ${uiState.data[0].location.name}")
            }

            is UiState.Error -> {
                Log.i("MainActivity", "onCreate: UiState.Error")
            }
        }
    }
}