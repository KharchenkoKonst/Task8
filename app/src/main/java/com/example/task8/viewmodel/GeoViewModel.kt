package com.example.task8.viewmodel

import androidx.lifecycle.ViewModel
import com.example.task8.LocationService

class GeoViewModel : ViewModel() {
    lateinit var LocationData: LocationService

    fun setLocationService(locationService: LocationService) {
        this.LocationData = locationService
    }
}