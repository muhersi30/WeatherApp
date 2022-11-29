package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.weatherapp.ViewModels.CurrentConditionsViewModel
import com.example.weatherapp.Views.Routes
import com.example.weatherapp.domain.Domain.Resource
import com.example.weatherapp.domain.Domain.forecastModel.Main
import com.example.weatherapp.domain.Domain.forecastModel.Weather
import com.example.weatherapp.domain.Domain.weatherModel.CurrentConditionsDataModel
import com.example.weatherapp.domain.Domain.weatherModel.Sys
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener

@Composable
fun CurrentTemperature(
    lat : Double,
    long : Double,
    navController: NavHostController
) {
    showCurrentWeatherButton(lat = lat, long = long, nc = navController)
}

@Composable
fun showCurrentWeatherButton(lat : Double, long : Double, nc : NavHostController, vm: CurrentConditionsViewModel = hiltViewModel()) {

    var isButtonVisible by remember { mutableStateOf(true) }
    var isPlaceNameVisible by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Row {
        Column {
            if (isButtonVisible) {
                Button(onClick = {
                    vm.getWeather(lat = lat, long = long)
                    isButtonVisible = false
                }, modifier = Modifier.fillMaxWidth() ) {
                    Text(text = "Get weather for my location", color = Color.White, fontSize = 20.sp)
                }
            }

        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPlaceNameVisible) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text, fontSize = 25.sp)
        }
    }

    val state = vm._state.value

    when (state) {
        is Resource.Loading -> {

            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)

            )
        }

        is Resource.Success -> {
            isButtonVisible = false
            isPlaceNameVisible = true

            for (i in state.data!!.weather) {
                 CurrentWeather(navController = nc, currentData = state.data.main, weather = i, sys = state.data.sys, dt = state.data.dt)
            }
            state.data.name.let {
                text = it.toString()
            }
        }

        is Resource.Error -> {
            Log.d("StateError", state.message.toString())

        }
    }
}

@Composable
fun CurrentWeather(navController: NavHostController, currentData: Main, weather: Weather, sys: Sys, dt:Int) {

    Column {
        Row {
            Column (modifier = Modifier
                .weight(2f)){
                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    currentData.temp.toString() + "°", fontSize = 65.sp,
                    modifier = Modifier.padding(start = 25.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    stringResource(R.string.feels_like) + " " + (currentData.feelsLike) + "°",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 25.dp)
                )

                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    stringResource(R.string.low) + " " + currentData.tempMin + "°",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 25.dp)
                )
                Text(
                    stringResource(R.string.high) + " " + currentData.tempMax + "°",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 25.dp)
                )
                Text(
                    stringResource(R.string.humidity) + " " + currentData.humidity,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 25.dp)
                )
                Text(
                    stringResource(R.string.pressure) + " " + currentData.pressure,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 25.dp)
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
            Column(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = weather.icon.toInt()),
                    modifier = Modifier
                        .height(250.dp)
                        .width(250.dp)
                    ,
                    contentDescription = null
                )
            }
            Log.d("Icon", weather.icon)
        }

        Row {
            Button(onClick = {
                navController.navigate(Routes.Forecast.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .weight(1f)) {
                Text(text = "Forecast", color = Color.White, fontSize = 20.sp)
            }
        }
    }

}