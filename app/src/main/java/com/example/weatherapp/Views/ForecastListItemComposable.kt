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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.domain.Domain.forecastModel.City
import com.example.weatherapp.domain.Domain.forecastModel.ForecastDTO
import com.example.weatherapp.domain.Domain.forecastModel.Weather

@Composable
fun ForecastListItem(city: City, forecastDTO: ForecastDTO,weather: Weather) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White

    ) {
        Row {
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
                    if(city.sunrise < forecastDTO.dt && forecastDTO.dt < city.sunset){weather.icon = R.drawable.firstd.toString()}
                    else{weather.icon = R.drawable.firstn.toString()}
                }
                "Clouds" -> {
                    if(city.sunrise < forecastDTO.dt && forecastDTO.dt < city.sunset){weather.icon = R.drawable.secondd.toString()}
                    else{weather.icon = R.drawable.secondn.toString()}
                }
                else -> {
                    weather.icon = R.drawable.fiftyd.toString()
                }
            }
            Image(
                painter = painterResource(id = weather.icon.toInt()),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
                    .height(60.dp)
                    .width(60.dp),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
//                Text(
//                    //forecast.date, fontSize = 18.sp,
//                    modifier = Modifier.padding(end = 10.dp))

            }
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    "Temp: " + forecastDTO.main.temp, fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .padding(start = 10.dp)
                )
                Text(
                    "High: " + forecastDTO.main.tempMax + " Low: " + forecastDTO.main.tempMin,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .padding(start = 10.dp)
                )

            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    "Sunrise: " + getDateAndTime.convertLongToTime(city.sunrise.toLong()) + "am",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    "Sunset: " + getDateAndTime.convertLongToTime(city.sunset.toLong()) + "pm",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(bottom = 10.dp)
                )

            }
        }
    }
}