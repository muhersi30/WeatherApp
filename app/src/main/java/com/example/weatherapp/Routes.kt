package com.example.weatherapp

sealed class Routes(val route: String) {
    object CurrentTemperature : Routes("current")
    object Forecast : Routes("forecast")
}