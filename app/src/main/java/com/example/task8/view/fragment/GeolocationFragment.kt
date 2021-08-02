package com.example.task8.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.task8.LocationService
import com.example.task8.databinding.FragmentGeolocationBinding
import com.example.task8.view.activity.MainActivity
import com.example.task8.viewmodel.GeoViewModel
import com.example.task8.viewmodel.GeoViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GeolocationFragment : Fragment() {
    private lateinit var binding: FragmentGeolocationBinding

    private val viewModelFactory: GeoViewModelFactory by lazy { GeoViewModelFactory() }
    private val viewModel: GeoViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeolocationBinding.inflate(layoutInflater)
//        requirePermissions()
        init()
//        subscribeToVM()
        return binding.root
    }

    private lateinit var locationClient: FusedLocationProviderClient

    private fun init() {
        locationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            val permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            ActivityCompat.requestPermissions(context as Activity, permissions.toTypedArray(), 0)

        }
        locationClient.lastLocation.addOnSuccessListener { location: Location? ->
            binding.geolocationTextView.text = "${location?.latitude} : ${location?.longitude}"
        }
//        viewModel.setLocationService(LocationService(requireContext()))
    }

    private fun subscribeToVM() {
        viewModel.LocationData.getCurrentPosition().observe(viewLifecycleOwner, {
            Log.i("observer", it.provider)
            binding.geolocationTextView.text = "${it.longitude} : ${it.latitude}"
        })
    }

    private fun requirePermissions() {

    }
}
