package com.example.weatherapp.utils

import com.example.weatherapp.data.WeatherData

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

fun sortedListByWord(list: ArrayList<WeatherData>): List<WeatherData>{
    return list.sortedBy { it.city }
}