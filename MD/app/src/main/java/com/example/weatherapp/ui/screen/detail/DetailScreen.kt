package com.example.weatherapp.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun DetailScreen(
    cityName: String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Log.i("DetailWeather", "DetailWeather: $cityName")
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        MainCard(navigateBack = navigateBack)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Today")
            Text(text = "7 Days")
        }
        LazyColumn {
        }
    }
}

@Composable
fun MainCard(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(600.dp),
        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
    ) {
        Box(
            modifier = modifier.background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF9EE9FF),
                        Color(0xFF007BFF)
                    )
                )
            )
        )
        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(navigateBack = navigateBack)
            WeatherInCard()
            GridWeatherComponent()
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = modifier
                .clickable {
                    navigateBack()
                },
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back",
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Semarang"
            )
            Text(
                text = "Jawa Tengah, Indonesia"
            )
        }
        Icon(
            modifier = modifier.size(32.dp),
            imageVector = Icons.Rounded.Bookmark,
            contentDescription = "Bookmark"
        )
    }
}

@Composable
fun WeatherInCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(300.dp)
            .padding(36.dp),
        backgroundColor = Color(0x32FFFFFF),
        border = BorderStroke(1.dp, Color(0xB3FFFFFF)),
        shape = RoundedCornerShape(30.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Cloudy"
            )
            Icon(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp),
                imageVector = Icons.Rounded.WbCloudy,
                contentDescription = null,
                tint = Color(0xFFA8C5F8)
            )
        }
    }
}

@Composable
fun GridWeatherComponent(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        item {
            Column(
                modifier = modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(36.dp),
                    imageVector = Icons.Rounded.Thermostat,
                    contentDescription = null
                )
                Text(text = "Celcius")
                Text(text = "20")
            }
        }
        item {
            Column(
                modifier = modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(36.dp),
                    imageVector = Icons.Rounded.Air,
                    contentDescription = null
                )
                Text(text = "Wind")
                Text(text = "20")
            }
        }
        item {
            Column(
                modifier = modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(36.dp),
                    imageVector = Icons.Rounded.Flare,
                    contentDescription = null
                )
                Text(text = "UV Index")
                Text(text = "20")
            }
        }
        item {
            Column(
                modifier = modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(36.dp),
                    imageVector = Icons.Rounded.Thermostat,
                    contentDescription = null
                )
                Text(text = "Fahrenheit")
                Text(text = "20")
            }
        }
        item {
            Column(
                modifier = modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(36.dp),
                    imageVector = Icons.Rounded.Speed,
                    contentDescription = null
                )
                Text(text = "Air Pressure")
                Text(text = "20")
            }
        }
        item {
            Column(
                modifier = modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier.size(36.dp),
                    imageVector = Icons.Rounded.WaterDrop,
                    contentDescription = null
                )
                Text(text = "Humidity")
                Text(text = "20")
            }
        }
    }
}

@Preview(showBackground = false, widthDp = 432)
@Composable
fun DetailWeatherPreview() {
    WeatherAppTheme {
        MainCard(navigateBack = {})
    }
}