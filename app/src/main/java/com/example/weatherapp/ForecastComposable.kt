package com.example.weatherapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Forecast(navController: NavHostController) {
    val forecastItems = remember { DataProviderOfComposables.forecastList }
    LazyColumn(
        modifier = Modifier.padding(bottom = 50.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = forecastItems,
            itemContent = {
                ForecastListItem(forecast = it)
            })
    }

    Column(modifier = Modifier.padding(start = 20.dp).padding(end = 20.dp).padding(bottom = 5.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            navController.navigateUp()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Go Back", color = Color.White, fontSize = 20.sp)
        }
    }
}
