package com.example.weatherapp

import com.example.weatherapp.domain.Domain.weatherModel.CurrentConditionsDataModel
import com.example.weatherapp.domain.Domain.forecastModel.ForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather?lat=46.7296&lon=94.6859&appid=4fe551ca32658293e12b22f1da987e2c")
    suspend fun getCurrentWeatherData(): Response<CurrentConditionsDataModel>

    @GET("data/2.5/forecast?")
    suspend fun getForecastData(
        @Query("appid") appid : String = "4fe551ca32658293e12b22f1da987e2c",
        @Query("lat") lat : Double = 46.7296,
        @Query("lon") lon : Double = 94.6859
    ) : Response<ForecastModel>
}
