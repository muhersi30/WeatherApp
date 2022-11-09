package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.ApiService
import com.example.weatherapp.domain.Domain.Resource
import com.example.weatherapp.domain.Domain.forecastModel.ForecastModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class ForecastRepositoryImpl@Inject constructor(private val  apiService : ApiService) : ForecastRepository {

    override suspend fun getForecast() : Flow<Resource<List<ForecastModel>>> = flow {
        emit(Resource.Loading())
        val response = apiService.getForecastData()
        try {
            if (response.isSuccessful){
                emit(Resource.Success(listOf(response.body()!!)))
                Log.d("State","success")
            }else{
                Log.d("Error","unsuccessful")
            }
        }catch (e : HttpException){
            emit(Resource.Error(e.message.toString()))
            Log.d("ErrorA",e.message.toString())
        }
    }

}