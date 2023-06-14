package com.example.weatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.*

fun doubleToInt(number: Double): String{
    val resultInteger: Int
    val a = number.toString().split(".")
    return if (a[1] == "0"){
        resultInteger = a[0].toInt()
        resultInteger.toString()
    }else{
        number.toString()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormatter(date: String): String{
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-LL-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("EEEE, dd LLLL yyyy", Locale("id", "ID"))

    val localDate = LocalDate.parse(date, inputFormatter)
    return localDate.format(outputFormatter)
}

fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * PI
        val x = cos(angleRad).toFloat() //Fractional x
        val y = sin(angleRad).toFloat() //Fractional y

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)