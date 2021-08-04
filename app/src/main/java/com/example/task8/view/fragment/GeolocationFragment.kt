package com.example.task8.view.fragment

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.task8.LocationService
import com.example.task8.databinding.FragmentGeolocationBinding
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

    private lateinit var locationClient: LocationService

    private fun init() {
        locationClient = LocationService(requireContext())
        viewModel.setLocationService(locationClient)

        viewModel.location.observe(viewLifecycleOwner, {
            val pos = "${it.longitude} : ${it.latitude}"
            Log.i("location", pos)
            binding.geolocationTextView.text = pos
        })
//        locationClient.lastLocation.addOnSuccessListener { location: Location? ->
//            binding.geolocationTextView.text = "${location?.latitude} : ${location?.longitude}"
    }

//        viewModel.setLocationService(LocationService(requireContext()))

//    private fun subscribeToVM() {
//        viewModel.LocationData.getCurrentPosition().observe(viewLifecycleOwner, {
//            Log.i("observer", it.provider)
//            binding.geolocationTextView.text = "${it.longitude} : ${it.latitude}"
//        })
//    }

//    private fun requestPermissions() {
//        val permissions = listOf(
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        )
//        ActivityCompat.requestPermissions(requireActivity(), permissions.toTypedArray(), 0)
//    }

}
