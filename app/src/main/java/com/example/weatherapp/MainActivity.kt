package com.example.weatherapp

import android.Manifest
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ViewModels.CurrentConditionsViewModel
import com.example.weatherapp.Views.Routes
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val REQUEST_PERMISSION_REQUEST_CODE = 2020
   // val currentConditionsViewModel by viewModels<CurrentConditionsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  setContentView(R.layout.activity_main)
        getSupportActionBar()?.setTitle(R.string.app_name);
        getCurrentLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_REQUEST_CODE && grantResults.size > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            }
            else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Please turn on Location Sharing to get weather information")
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener {
                            dialog, id -> finish()
                    })

                val alert = dialogBuilder.create()
                alert.setTitle("Location")
                alert.show()
            }
        }
    }
    private fun getCurrentLocation() {

        var locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                ,REQUEST_PERMISSION_REQUEST_CODE)
            return
        }
        var progressBar = ProgressDialog(this)
        progressBar.setCancelable(true);
        progressBar.setMessage("Loading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();

        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest,object:LocationCallback(){
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)

                    LocationServices.getFusedLocationProviderClient(this@MainActivity).removeLocationUpdates(this)

                    if(p0 != null && p0.locations.size > 0) {
                        LocationServices.getFusedLocationProviderClient(this@MainActivity).removeLocationUpdates(this)
                        var locIndex = p0.locations.size - 1

                        var lat = p0.locations.get(locIndex).latitude
                        var long = p0.locations.get(locIndex).longitude

                        progressBar.dismiss()

                        setContent {
                             screenMain(lat = lat, long = long)
                        }
                    }
                }
            }, Looper.getMainLooper())
    }
    @Composable
    fun screenMain(lat: Double, long: Double) {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.CurrentTemperature.route) {

            composable(Routes.CurrentTemperature.route) {
                CurrentTemperature(lat = lat, long = long, navController = navController)
            }

            composable(Routes.Forecast.route) {
                Forecast(navController = navController, lat = lat, long = long)
            }
        }
    }
}