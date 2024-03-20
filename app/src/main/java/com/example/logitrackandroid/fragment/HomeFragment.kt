package com.example.logitrackandroid.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.logitrackandroid.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.shashank.sony.fancytoastlib.FancyToast


class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location ->
                // Update the map with the new location
                updateLocation(location)
            }
        }
    }

    private val permissionRequestCode = 1
    private val locationUpdateInterval = 1000L // Update every 1 second (adjust as needed)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val currentLocationButton = rootView.findViewById<AppCompatImageButton>(R.id.currentLocationButton)
        val liveLocationButton = rootView.findViewById<AppCompatImageButton>(R.id.liveLocationButton)

        mapView = rootView.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        // Setup button click listeners
        currentLocationButton.setOnClickListener {
            showCurrentLocation()
           // Toast.makeText(requireContext(), "Fetching current location...", Toast.LENGTH_SHORT).show()
            FancyToast.makeText(requireContext(),"Fetching current location...",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show()
        }

        liveLocationButton.setOnClickListener {
            startLiveLocationUpdates()
        }

        return rootView
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        googleMap.uiSettings.isZoomControlsEnabled = true

        // Check for location permissions
        if (hasLocationPermission()) {
            startLocationUpdates()
        } else {
            requestLocationPermission()
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), permissionRequestCode
        )
    }

    private fun startLocationUpdates() {
        if (hasLocationPermission()) {
            val locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(locationUpdateInterval)

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                try {
                    fusedLocationClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        null
                    )
                } catch (e: SecurityException) {
                    // Handle SecurityException
                    e.printStackTrace()
                }
            }
        } else {
            // Request location permissions
            requestLocationPermission()
        }
    }

    private fun showCurrentLocation() {
        if (hasLocationPermission()) {
            try {
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        location?.let {
                            updateLocation(it)
                        }
                    }
            } catch (e: SecurityException) {
                // Handle SecurityException
               // Toast.makeText(requireContext(), "error:" + e.printStackTrace(), Toast.LENGTH_SHORT).show()
                FancyToast.makeText(requireContext(),"error:" + e.printStackTrace(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
            }
        } else {
            // Request location permissions
            requestLocationPermission()
        }
    }

    private fun startLiveLocationUpdates() {
        if (hasLocationPermission()) {
            val locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(locationUpdateInterval)

            try {
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    null
                )
                Toast.makeText(requireContext(), "Live location updates enabled", Toast.LENGTH_SHORT).show()
            } catch (e: SecurityException) {
                // Handle SecurityException
                e.printStackTrace()
            }
        } else {
            Toast.makeText(requireContext(), "Permission not granted", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                // Handle permission denied case (inform user, retry, etc.)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private var currentLocationMarker: Marker? = null

    private fun updateLocation(location: Location) {
        val latLng = LatLng(location.latitude, location.longitude)

        // Move camera to the current location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

        // Add or update marker for current location
        if (currentLocationMarker == null) {
            currentLocationMarker =
                googleMap.addMarker(MarkerOptions().position(latLng).title("Current Location"))
        } else {
            currentLocationMarker?.position = latLng
        }
    }
}
