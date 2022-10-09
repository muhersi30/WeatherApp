package com.example.weatherapp

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun CurrentTemperature(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(stringResource(R.string.place_name), fontSize = 25.sp)
    }
    Row {
        Column {
            Spacer(modifier = Modifier.height(70.dp))
            Text(stringResource(R.string.temperature_value), fontSize = 75.sp,
                modifier = Modifier.padding(start = 60.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Text(stringResource(R.string.feels_like) + " " + stringResource(R.string.feels_like_value), fontSize = 20.sp,
                modifier = Modifier.padding(start = 60.dp))

            Spacer(modifier = Modifier.height(60.dp))
            Text(stringResource(R.string.low) + " " + stringResource(R.string.low_value), fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp))
            Text(stringResource(R.string.high) + " " + stringResource(R.string.high_value), fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp))
            Text(stringResource(R.string.humidity) + " " + stringResource(R.string.humidity_value), fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp))
            Text(stringResource(R.string.pressure) + " " + stringResource(R.string.pressure_value), fontSize = 25.sp,
                modifier = Modifier.padding(start = 30.dp))
        }
        Image(
            painter = painterResource(id = R.drawable.sun),
            modifier = Modifier
                .padding(top = 65.dp)
                .height(140.dp)
                .width(140.dp),
            contentDescription = null
        )
    }
    Row() {

        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(400.dp))
            Button(onClick = {
                navController.navigate(Routes.Forecast.route)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Forecast", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}
