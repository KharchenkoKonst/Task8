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

class LocationService(private val context: Context) {
    private var _currentPosition = MutableLiveData<Location>()
    fun getCurrentPosition(): LiveData<Location> {
        return _currentPosition
    }

    init {
        initLocation()
    }

    private fun initLocation() {
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
            return
        }
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0L,
            0f,
            locationListener
        )
        _currentPosition.value =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        Log.i("current position", _currentPosition.value?.longitude.toString())
    }
}