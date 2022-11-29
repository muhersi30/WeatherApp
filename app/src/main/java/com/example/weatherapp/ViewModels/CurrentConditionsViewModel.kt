package com.example.weatherapp.ViewModels

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.Domain.weatherModel.CurrentConditionsDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.weatherapp.domain.Domain.Resource
import com.example.weatherapp.repository.WeatherRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val weatherRepository: WeatherRepositoryImpl) :
    ViewModel() {

    val _state: MutableState<Resource<CurrentConditionsDataModel>?> = mutableStateOf(null)

    fun getWeather(lat : Double, long : Double) {
        viewModelScope.launch {
            weatherRepository.getWeather(latt = lat, longg = long).collect {
                _state.value = it
            }
        }
    }
}