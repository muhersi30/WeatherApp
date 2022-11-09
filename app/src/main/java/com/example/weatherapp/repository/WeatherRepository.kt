package com.example.weatherapp.repository

import com.example.weatherapp.domain.Domain.weatherModel.CurrentConditionsDataModel
import com.example.weatherapp.domain.Domain.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(): Flow<Resource<CurrentConditionsDataModel>>
}