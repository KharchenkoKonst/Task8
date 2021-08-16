package com.example.task8.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.task8.databinding.FragmentGeolocationBinding
import com.google.android.gms.location.*

class GeolocationFragment : Fragment() {
    private lateinit var binding: FragmentGeolocationBinding

//    private val viewModelFactory: GeoViewModelFactory by lazy { GeoViewModelFactory() }
//    private val viewModel: GeoViewModel by viewModels { viewModelFactory }


    lateinit var locationService: LocationService
    private val ID_LOCATION_PERMISSION = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeolocationBinding.inflate(layoutInflater)
        locationService = LocationService().apply { getLastLocation() }
        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == ID_LOCATION_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                locationService.getLastLocation()
            }
        }
    }


    inner class LocationService {

        private val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        @SuppressLint("MissingPermission")
        fun getLastLocation() {
            if (checkPermissions()) {
                if (isLocationEnabled()) {

                    fusedLocationClient.requestLocationUpdates(
                        LocationRequest.create().apply {
                            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                            interval = 0
                            fastestInterval = 0
                            numUpdates = 1
                        },
                        object : LocationCallback() {
                            override fun onLocationResult(p0: LocationResult) {
                                binding.geolocationTextView.text =
                                    "${p0.lastLocation.latitude} : ${p0.lastLocation.longitude}"
                            }
                        }, Looper.getMainLooper()
                    )
                } else {
                    Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }
            } else {
                requestPermissions()
                getLastLocation()
            }
        }

        private fun isLocationEnabled(): Boolean {
            val locationManager: LocationManager =
                requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        }

        private fun checkPermissions(): Boolean {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            }
            return false
        }

        private fun requestPermissions() {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                ID_LOCATION_PERMISSION
            )
        }
    }
}
