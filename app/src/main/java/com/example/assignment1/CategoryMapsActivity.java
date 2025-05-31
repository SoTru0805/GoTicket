package com.example.assignment1;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.assignment1.databinding.ActivityCategoryMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ActivityCategoryMapsBinding binding;
    private Geocoder geocoder;
    // class variable to hold country name
    private String countryToFocus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("GoogleMaps", "onCreate: ");

        binding = ActivityCategoryMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        countryToFocus = getIntent().getExtras().getString("SelectedCountry", " ");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findCountryMoveCamera();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        // place latitude-longitude values in the order specified
        LatLng CatLocation = new LatLng(0, 0);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(CatLocation));
    }

    private void findCountryMoveCamera() {
        // initialise Geocode to search location using String
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        // getFromLocationName method works for API 33 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            geocoder.getFromLocationName(countryToFocus, 1, addresses -> {
                // if there are results, this condition would return true
                if (!addresses.isEmpty()) {
                    // run on UI thread as the user interface will update once set map location
                    runOnUiThread(() -> {
                        // define new LatLng variable using the first address from list of addresses
                        LatLng newAddressLocation = new LatLng(addresses.get(0).getLatitude(),
                                addresses.get(0).getLongitude()
                        );
                        // repositions the camera according to newAddressLocation
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(newAddressLocation));
                        // just for reference add a new Marker
                        mMap.addMarker(new MarkerOptions().position(newAddressLocation).title(countryToFocus));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                    });
                }else {
                    runOnUiThread(() -> Toast.makeText(this, "Category address not found", Toast.LENGTH_SHORT).show());
                }
            });
        }
    }
}
