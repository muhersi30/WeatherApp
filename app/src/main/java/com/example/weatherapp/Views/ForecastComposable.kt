package com.example.weatherapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.domain.Domain.Resource
import com.example.weatherapp.ViewModels.ForecastViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Forecast(
    navController: NavHostController,
    forecastViewModel: ForecastViewModel = hiltViewModel()
) {
    val state = forecastViewModel._state.value
    Log.d("LIST", state!!.data.toString())

    Column() {
        when (state) {

            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is Resource.Success -> {
                LazyColumn() {
                    items(state.data!!) {
                        for (item in it.list!!) {
                            for (i in item.weather)
                            ForecastListItem(city = it.city!!, forecastDTO = item, weather = i)
                        }
                    }
                }
            }

            is Resource.Error -> {
                Log.d("StateError", state.message.toString())

            }
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 20.dp)
            .padding(end = 20.dp)
            .padding(bottom = 5.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            navController.navigateUp()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Go Back", color = Color.White, fontSize = 20.sp)
        }
    }
}
