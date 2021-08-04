package com.example.task8.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.task8.LocationService

class GeoViewModel : ViewModel() {
    private lateinit var locationData: LocationService
    lateinit var location: LiveData<Location>

    fun setLocationService(locationService: LocationService) {
        this.locationData = locationService
        location = locationData.getCurrentPosition()
    }
}