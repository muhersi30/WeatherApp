package com.example.weatherapp.repository

import com.example.weatherapp.domain.Domain.Resource
import com.example.weatherapp.domain.Domain.forecastModel.ForecastModel
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecast(lat: Double, long: Double) : Flow<Resource<List<ForecastModel>>>
}