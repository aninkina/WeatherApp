package dev.fstudio.weather.util

import android.Manifest
import android.app.Activity
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient

object PermissionUtil {

    const val MY_PERMISSIONS_REQUEST_LOCATION = 99

    fun checkLocationPermission(activity: Activity, fusedLocationProvider: FusedLocationProviderClient, preferences:SharedPreferences) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {

                AlertDialog.Builder(activity)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton("OK") { _, _ ->
                        requestLocationPermission(activity)
                    }
                    .create()
                    .show()
            } else {
                requestLocationPermission(activity)
            }
        } else {
            fusedLocationProvider.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    preferences.edit()
                        .putFloat("lat", location.latitude.toFloat())
                        .putFloat("lon", location.longitude.toFloat())
                        .apply()
                }
            }
        }
    }

    private fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    }
}