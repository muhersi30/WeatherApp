package com.example.weatherapp.domain.Domain.forecastModel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastModel(
    @Json(name = "city")
    val city: City? = null,
    @Json(name = "cnt")
    val cnt: Int ? = null,
    @Json(name = "cod")
    val cod: String ? = null,
    @Json(name = "list")
    val list: List<ForecastDTO> ?= null,
    @Json(name = "message")
    val message: Int ? = null
)