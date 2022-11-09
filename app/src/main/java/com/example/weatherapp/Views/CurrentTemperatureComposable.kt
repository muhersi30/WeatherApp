package com.example.weatherapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.weatherapp.domain.Domain.Resource
import com.example.weatherapp.ViewModels.CurrentConditionsViewModel
import com.example.weatherapp.Views.Routes
import com.example.weatherapp.domain.Domain.forecastModel.Main
import com.example.weatherapp.domain.Domain.forecastModel.Weather
import com.example.weatherapp.domain.Domain.weatherModel.Sys

@Composable
fun CurrentTemperature(
    navController: NavHostController,
    weatherViewModel: CurrentConditionsViewModel = hiltViewModel()
) {
    val state = weatherViewModel._state.value
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text, fontSize = 25.sp)

        when (state) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is Resource.Success -> {
                for (i in state.data!!.weather) {
                    CurrentWeather(currentData = state.data.main, weather = i, sys = state.data.sys, dt = state.data.dt)
                }
                state.data.name.let {
                    text = it.toString()
                }
            }

            is Resource.Error -> {
                Log.d("StateError", state.message.toString())

            }
        }

        Button(onClick = {
            navController.navigate(Routes.Forecast.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Forecast", color = Color.White, fontSize = 20.sp)
        }
    }
}


@Composable
fun CurrentWeather(currentData: Main, weather: Weather, sys: Sys, dt:Int) {
    Row {
//        Column {
//            Spacer(modifier = Modifier.height(70.dp))
//            Text(stringResource(R.string.temperature_value), fontSize = 75.sp,
//                modifier = Modifier.padding(start = 60.dp))
//            Spacer(modifier = Modifier.height(5.dp))
//            Text(stringResource(R.string.feels_like) + " " + stringResource(R.string.feels_like_value), fontSize = 20.sp,
//                modifier = Modifier.padding(start = 60.dp))
//
//            Spacer(modifier = Modifier.height(60.dp))
//            Text(stringResource(R.string.low) + " " + stringResource(R.string.low_value), fontSize = 25.sp,
//                modifier = Modifier.padding(start = 30.dp))
//            Text(stringResource(R.string.high) + " " + stringResource(R.string.high_value), fontSize = 25.sp,
//                modifier = Modifier.padding(start = 30.dp))
//            Text(stringResource(R.string.humidity) + " " + stringResource(R.string.humidity_value), fontSize = 25.sp,
//                modifier = Modifier.padding(start = 30.dp))
//            Text(stringResource(R.string.pressure) + " " + stringResource(R.string.pressure_value), fontSize = 25.sp,
//                modifier = Modifier.padding(start = 30.dp))
//        }

        Column {
            Spacer(modifier = Modifier.height(70.dp))
            // RIGHT NOW ONLY TEMP IS SET FOR TESTING. ALL VALUES NEED TO BE SET FROM THE MODEL.
            Text(
                currentData.temp.toString(), fontSize = 75.sp,
                modifier = Modifier.padding(start = 60.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                stringResource(R.string.feels_like) + " " + (currentData.feelsLike),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 60.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))
            Text(
                stringResource(R.string.low) + " " + currentData.tempMin,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
            Text(
                stringResource(R.string.high) + " " + currentData.tempMax,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
            Text(
                stringResource(R.string.humidity) + " " + currentData.humidity,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
            Text(
                stringResource(R.string.pressure) + " " + currentData.pressure,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
        }

        when (weather.main) {
            "Thunderstorm" -> {
                weather.icon = R.drawable.elevend.toString()
            }
            "Drizzle" -> {
                weather.icon = R.drawable.ninthd.toString()
            }
            "Rain" -> {
                weather.icon = R.drawable.tenthday.toString()
            }
            "Snow" -> {
                weather.icon = R.drawable.thirtheenn.toString()
            }
            "Clear" -> {
                if(sys.sunrise < dt && dt < sys.sunset){weather.icon = R.drawable.firstd.toString()}
                else{weather.icon = R.drawable.firstn.toString()}
            }
            "Clouds" -> {
                if(sys.sunrise < dt && dt < sys.sunset){weather.icon = R.drawable.secondd.toString()}
                else{weather.icon = R.drawable.secondn.toString()}
            }


            else -> {
                weather.icon = R.drawable.fiftyd.toString()
            }
        }
        Image(
            painter = painterResource(id = weather.icon.toInt()),
            modifier = Modifier
                .padding(top = 65.dp)
                .height(140.dp)
                .width(140.dp),
            contentDescription = null
        )
        Log.d("Icon", weather.icon)
    }
}

