package com.example.yapeapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yapeapp.databinding.FragmentLocationBinding
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * @author Axel Sanchez
 */
class LocationFragment : Fragment() {

    private var fragmentLocationBinding: FragmentLocationBinding? = null
    private val binding get() = fragmentLocationBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentLocationBinding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentLocationBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val latitude = it.getString(DetailsFragment.ID_LATITUDE)?.toDouble()?:0.0
            val longitude = it.getString(DetailsFragment.ID_LONGITUDE)?.toDouble()?:0.0

            val latLng = LatLng(latitude, longitude)
            val supportMapFragment = childFragmentManager.findFragmentById(binding.fMap.id) as SupportMapFragment?

            supportMapFragment?.getMapAsync { googleMap ->

                googleMap.addMarker(MarkerOptions().position(latLng))
                val cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 7f)
                googleMap.moveCamera(cameraUpdate)
                googleMap.animateCamera(cameraUpdate)

            }
        }
    }
}