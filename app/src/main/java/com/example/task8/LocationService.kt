package com.example.task8

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task8.view.activity.MainActivity

class LocationService(context: Context) {
    private var _currentPosition = MutableLiveData<Location>()
    fun getCurrentPosition(): LiveData<Location> {
        return _currentPosition
    }

    init {
        val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                _currentPosition.value = location
            }

        }
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            ActivityCompat.requestPermissions(
                context as MainActivity,
                permissions.toTypedArray(),
                0
            )
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0L,
            0f,
            locationListener
        )
        _currentPosition.value =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        Log.i("current position", _currentPosition.value?.longitude.toString())
    }
}