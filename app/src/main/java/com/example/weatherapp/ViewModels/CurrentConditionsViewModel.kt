package com.example.weatherapp.ViewModels

import androidx.compose.runtime.*
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

    init {
        viewModelScope.launch {
            weatherRepository.getWeather().collect {
                _state.value = it
            }
        }
    }

}