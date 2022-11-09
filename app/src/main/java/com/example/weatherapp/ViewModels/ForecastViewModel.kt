package com.example.weatherapp.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.Domain.Resource
import com.example.weatherapp.domain.Domain.forecastModel.ForecastModel
import com.example.weatherapp.repository.ForecastRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val forecastRepository: ForecastRepositoryImpl) :
    ViewModel() {

    val _state: MutableState<Resource<List<ForecastModel>>?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            forecastRepository.getForecast().collect {
                _state.value = it
            }
        }
    }

}
