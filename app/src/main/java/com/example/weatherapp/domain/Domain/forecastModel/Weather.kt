package com.example.weatherapp.domain.Domain.forecastModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    val description: String,
    @Json(name = "icon")
    var icon: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "main")
    val main: String
)