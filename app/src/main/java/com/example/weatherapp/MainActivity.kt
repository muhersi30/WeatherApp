package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // THIS LINE IS COMMENTED AS WE ARE NOW USING COMPOSABLES TO MAKE THE SAME UI.
       // setContentView(R.layout.activity_main)

        // Setting name of app in title bar
        getSupportActionBar()?.setTitle(R.string.app_name);

        setContent {
            screenMain()
        }
    }

    @Composable
    fun screenMain() {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.CurrentTemperature.route) {

            composable(Routes.CurrentTemperature.route) {
                CurrentTemperature(navController = navController)
            }

            composable(Routes.Forecast.route) {
                Forecast(navController = navController)
            }
        }
    }
}