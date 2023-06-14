package com.example.weatherapp.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DoubleToIntTest{

    @Test
    fun doubleWithOneZeroBehind(){
        val double = 20.0
        assertEquals("20", doubleToInt(double))
    }

    @Test
    fun doubleWithNotZeroBehind(){
        val double = 20.1
        assertEquals(double.toString(), doubleToInt(double))
    }
}