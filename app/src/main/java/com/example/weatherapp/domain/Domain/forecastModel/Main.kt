package com.example.weatherapp.domain.Domain.forecastModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    val feelsLike: Double ? = null ,
    @Json(name = "grnd_level")
    val grndLevel: Int ? = null ,
    @Json(name = "humidity")
    val humidity: Int ? = null ,
    @Json(name = "pressure")
    val pressure: Int ? = null ,
    @Json(name = "sea_level")
    val seaLevel: Int ? = null,
    @Json(name = "temp")
    val temp: Double ? = null,
    @Json(name = "temp_kf")
    val tempKf: Double ? = null,
    @Json(name = "temp_max")
    val tempMax: Double ? = null,
    @Json(name = "temp_min")
    val tempMin: Double ? = null
)