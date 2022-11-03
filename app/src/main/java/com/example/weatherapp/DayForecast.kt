package com.example.weatherapp

data class DayForecast(
                       val date : String,
                       val sunrise: String,
                       val sunset : String,
                       val temp : ForecastTemp,
                       val pressure : Float,
                       val humidity : Int)
