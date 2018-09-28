package com.evaluation.www.wunderappg;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String carName, carVIN, carAddr, carEngType, carExt, carInt, carFuel;
    private double coordLat, coordLong,carCoord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        carName = intent.getStringExtra("car_name");
        carAddr = intent.getStringExtra("car_addr");
        coordLat = intent.getDoubleExtra("car_coordinate_lat", -34);
        coordLong = intent.getDoubleExtra("car_coordinate_long", 151);

      //  LatLng latlng = new LatLng(-34, 151);
        LatLng latlng = new LatLng(coordLat, coordLong);
        mMap.addMarker(new MarkerOptions().position(latlng).title(carName).snippet(carAddr)
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }




}
