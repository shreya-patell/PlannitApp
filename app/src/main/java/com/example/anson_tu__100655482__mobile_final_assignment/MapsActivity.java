package com.example.anson_tu__100655482__mobile_final_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    String eventAddress = "";
    GoogleMap gMap;
    SupportMapFragment mapFragment;
    Button backToEventDetailsBtn, goToInvitesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "ADD_GOOGLE_MAPS_API_KEY_HERE");
        }
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(
                Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));
        autocompleteSupportFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(43.623402, -79.549093),
                new LatLng(43.980626, -78.822587)
        ));

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMaps);
        mapFragment.getMapAsync(this);

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                String locationName = place.getName();
                String locationAddress = place.getAddress();
                LatLng locationLatLng = place.getLatLng();
                gMap.clear();
                eventAddress = locationName + ", " + locationAddress;
                if (locationLatLng != null) {
                    gMap.addMarker(new MarkerOptions().position(locationLatLng).title(locationName));
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, 15));
                }
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i("MAP_AUTOCOMPLETE_ERR", "An error occurred: " + status);
            }
        });

        backToEventDetailsBtn = findViewById(R.id.backToEventDetailsBtn);
        goToInvitesBtn = findViewById(R.id.goToInvitesBtn);

        backToEventDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goToInvitesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, EventInvitesActivity.class);

                intent.putExtra("keyEventName", getIntent().getStringExtra("keyEventName"));
                intent.putExtra("keyEventDescription", getIntent().getStringExtra("keyEventDescription"));
                intent.putExtra("keyEventDate", getIntent().getStringExtra("keyEventDate"));
                intent.putExtra("keyEventStartTime", getIntent().getStringExtra("keyEventStartTime"));
                intent.putExtra("keyEventEndTime", getIntent().getStringExtra("keyEventEndTime"));
                intent.putExtra("keyEventLocation", eventAddress);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
    }
}