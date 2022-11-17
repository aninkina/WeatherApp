package dev.fstudio.weather.ui.activity.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dev.fstudio.weather.R
import dev.fstudio.weather.databinding.ActivityMainBinding
import dev.fstudio.weather.util.Constants
import dev.fstudio.weather.util.MiscUtil
import dev.fstudio.weather.util.MiscUtil.checkIfFirstEntry
import dev.fstudio.weather.util.MiscUtil.getCity
import dev.fstudio.weather.util.MiscUtil.getSubString
import dev.fstudio.weather.util.PermissionUtil.MY_PERMISSIONS_REQUEST_LOCATION
import dev.fstudio.weather.util.PermissionUtil.checkLocationPermission

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var fusedLocationProvider: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkIfFirstEntry(MiscUtil.getDefaultSharedPreference(this))) {
            setDefaultLocation()
        }

        //requestLocationPermission()
        supportActionBar?.subtitle = getCity(MiscUtil.getDefaultSharedPreference(this))

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        checkLocationPermission(
            this, fusedLocationProvider,
            MiscUtil.getDefaultSharedPreference(this)
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return (NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp())
    }


    private fun setDefaultLocation() {
        MiscUtil.getDefaultSharedPreference(this).edit()
            .putFloat("lat", Constants.DEFAULT_LAT)
            .putFloat("lon", Constants.DEFAULT_LON)
            .putString("city", Constants.DEFAULT_CITY)
            .apply()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
//                        fusedLocationProvider.lastLocation.addOnSuccessListener { location: Location? ->
//                            if (location != null) {
//                                MiscUtil.getDefaultSharedPreference(this).edit()
//                                    .putFloat("lat", location.latitude.toFloat())
//                                    .putFloat("lon", location.longitude.toFloat())
//                                    .apply()
//                            }
//                        }
                    }

                } else {
                    startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", this.packageName, null),
                        ),
                    )
                }
                return
            }
        }
    }
}

