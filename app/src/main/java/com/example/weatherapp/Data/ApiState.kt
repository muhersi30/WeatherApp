package com.example.weatherapp.Data

import com.example.weatherapp.domain.Domain.forecastModel.ForecastModel


sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<ForecastModel>) : ApiState()
    object Empty : ApiState()
}
