package com.example.weatherapp.domain.Domain.weatherModel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country")
    val country: String ?= null,
    @Json(name = "id")
    val id: Int ?= null,
    @Json(name = "sunrise")
    val sunrise: Int,
    @Json(name = "sunset")
    val sunset: Int,
    @Json(name = "type")
    val type: Int ?= null
)
