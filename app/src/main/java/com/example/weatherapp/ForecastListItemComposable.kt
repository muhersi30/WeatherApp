package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForecastListItem(forecast: DayForecast) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White

    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.sun),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
                    .height(60.dp)
                    .width(60.dp),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)) {
                Text(
                    forecast.date, fontSize = 18.sp,
                    modifier = Modifier.padding(end = 10.dp))

            }
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.CenterVertically)) {
                Text(
                    "Temp: " + forecast.temp.overallTemp, fontSize = 14.sp,
                    modifier = Modifier.padding(top = 5.dp).padding(start = 10.dp))
                Text(
                    "High: " + forecast.temp.max + "  " + "Low: " + forecast.temp.min, fontSize = 14.sp,
                    modifier = Modifier.padding(top = 5.dp).padding(start = 10.dp))

            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)) {
                Text(
                    "Sunrise: " + forecast.sunrise + "am", fontSize = 14.sp,
                    modifier = Modifier.padding(top = 10.dp))
                Text(
                    "Sunset: " + forecast.sunset + "pm", fontSize = 14.sp,
                    modifier = Modifier.padding(top = 10.dp).padding(bottom = 10.dp))

            }
        }
    }
}