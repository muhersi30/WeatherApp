package com.example.weatherapp.Views

sealed class Routes(val route: String) {
    object CurrentTemperature : Routes("current")
    object Forecast : Routes("forecast")
}