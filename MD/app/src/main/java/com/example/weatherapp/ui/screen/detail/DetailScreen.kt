package com.example.weatherapp.ui.screen.detail

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.*
import com.example.weatherapp.utils.dateFormatter
import com.example.weatherapp.utils.doubleToInt
import com.example.weatherapp.utils.gradientBackground
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    val precipitation by remember { mutableStateOf(0.0) }
    val humidity by remember { mutableStateOf(0.0) }
    val wind by remember { mutableStateOf(0.0) }
    val avgTemp by remember { mutableStateOf(0.0) }
    val maxTemp by remember { mutableStateOf(0.0) }
    val minTemp by remember { mutableStateOf(0.0) }

    val weatherStatus by remember { mutableStateOf("Sunny") }
    val colors: List<Color>
    val weatherImage: Int
    when (weatherStatus) {
        "Sunny" -> {
            colors = bg_sunny
            weatherImage = R.drawable.ic_sunny
        }
        "Cloudy" -> {
            colors = bg_cloudy
            weatherImage = R.drawable.cloudy2
        }
        "Rainy" -> {
            colors = bg_rainy
            weatherImage = R.drawable.ic_rainy
        }
        "Mist" -> {
            colors = bg_mist
            weatherImage = R.drawable.ic_foggy
        }
        "Storm" -> {
            colors = bg_storm
            weatherImage = R.drawable.ic_storm
        }
        "Drizzly" -> {
            colors = bg_drizzly
            weatherImage = R.drawable.ic_drizzly
        }
        "Sun Shower" -> {
            colors = bg_sun_shower
            weatherImage = R.drawable.ic_sun_shower
        }
        else -> {
            colors = bg_partly_cloudy
            weatherImage = R.drawable.ic_partly_cloudy
        }
    }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    var date by remember { mutableStateOf("2023/06/13") }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            date = "$mYear/$mMonth/$mDayOfMonth"
        }, year, month, day
    )

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Section1(
            maxTemp = maxTemp,
            minTemp = minTemp,
            weatherStatus = weatherStatus,
            weatherImage = weatherImage,
            colors = colors,
            date = dateFormatter(date),
            OnClick = { datePickerDialog.show() }
        )
        Section2(
            precipitation = precipitation,
            humidity = humidity,
            wind = wind,
            avgTemp = avgTemp
        )
    }
}

@Composable
fun Section1(
    modifier: Modifier = Modifier,
    maxTemp: Double,
    minTemp: Double,
    weatherStatus: String,
    weatherImage: Int,
    colors: List<Color>,
    date: String,
    OnClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .gradientBackground(colors = colors, 150F)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // City and Region
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Semarang", style = MaterialTheme.typography.body1)
            Text(text = "Jawa Tengah, Indonesia", style = MaterialTheme.typography.body2)
        }
        Spacer(modifier = modifier.height(40.dp))
        // Calendar and Date
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .size(40.dp)
                    .clickable { OnClick() },
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = "Calendar"
            )
            Text(text = date, style = MaterialTheme.typography.h6)
        }
        // Weather Icon
        Image(
            modifier = modifier.size(300.dp),
            painter = painterResource(weatherImage),
            contentDescription = null
        )
        // Weather Status
        Text(text = weatherStatus, style = MaterialTheme.typography.h5)
        // Max Min Temp
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${doubleToInt(maxTemp)}°", style = MaterialTheme.typography.h2)
            Icon(
                modifier = modifier.size(32.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_up),
                contentDescription = null,
                tint = Color(0xFFAD3A3A)
            )

            Spacer(
                modifier = modifier
                    .width(2.5.dp)
                    .height(40.dp)
                    .background(Color(0xFF000000))
            )
            Icon(
                modifier = modifier.size(32.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = Color(0xFF59AD3B)
            )
            Text(text = "${doubleToInt(minTemp)}°", style = MaterialTheme.typography.h2)
        }
    }
}

@Composable
fun Section2(
    modifier: Modifier = Modifier,
    precipitation: Double,
    humidity: Double,
    wind: Double,
    avgTemp: Double
) {
    LazyVerticalGrid(
        modifier = modifier.background(Color(0xFF5A5A5A)),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        contentPadding = PaddingValues(24.dp, 48.dp)
    ) {
        item {
            GridLeftItem(text = "Temperature", value = "${doubleToInt(avgTemp)}℃", image = R.drawable.ic_termo)
        }
        item {
            GridRightItem(text = "Wind", value = "${doubleToInt(wind)}Km/h", image = R.drawable.ic_wind_speed)
        }
        item {
            GridLeftItem(text = "Humidity", value = "${doubleToInt(humidity)}%", image = R.drawable.ic_humidity)
        }
        item {
            GridRightItem(text = "Precipitation", value = "${doubleToInt(precipitation)}%", image = R.drawable.ic_rain_rate)
        }
    }
}

@Composable
fun GridLeftItem(
    modifier: Modifier = Modifier,
    text: String,
    value: String,
    image: Int
) {
    Row(
        horizontalArrangement = Arrangement.End,
    ) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = text, style = MaterialTheme.typography.body1.copy(
                    color = Color(0xFFC0C0C0),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = value, style = MaterialTheme.typography.h6.copy(
                    color = Color(0xFFFFFFFF)
                )
            )
        }
        Image(
            modifier = modifier.size(48.dp),
            painter = painterResource(image),
            contentDescription = null
        )
    }
}

@Composable
fun GridRightItem(
    modifier: Modifier = Modifier,
    text: String,
    value: String,
    image: Int
) {
    Row(
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = modifier.size(48.dp),
            painter = painterResource(image),
            contentDescription = null
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text, style = MaterialTheme.typography.body1.copy(
                    color = Color(0xFFC0C0C0),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = value, style = MaterialTheme.typography.h6.copy(
                    color = Color(0xFFFFFFFF)
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true, uiMode = 0)
@Composable
fun DetailWeatherPreview() {
    WeatherAppTheme {
        DetailScreen()
    }
}