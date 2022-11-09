package com.example.weatherapp.domain.Domain.weatherModel

import com.example.weatherapp.domain.Domain.forecastModel.Main
import com.example.weatherapp.domain.Domain.forecastModel.Weather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentConditionsDataModel(
    @Json(name = "main") val main: Main,
    @Json(name = "name")
    val name: String,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "sys")
    val sys: Sys,
    @Json(name = "dt")
    val dt: Int,
)