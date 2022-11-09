package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.ApiService
import com.example.weatherapp.domain.Domain.weatherModel.CurrentConditionsDataModel
import com.example.weatherapp.domain.Domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl@Inject constructor(private val  apiService : ApiService) : WeatherRepository {
    override suspend fun getWeather(): Flow<Resource<CurrentConditionsDataModel>> = flow {
        emit(Resource.Loading())
        val response = apiService.getCurrentWeatherData()
        try {
            if (response.isSuccessful){
                emit(Resource.Success(response.body()!!))
            }else{
                Log.d("Error","unsuccessful")
            }
        }catch (e : HttpException){
            emit(Resource.Error(e.message.toString()))
        }
    }


}