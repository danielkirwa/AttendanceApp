package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class AddGPS extends AppCompatActivity {
    TextView txtlat, txtlong;
    TextInputEditText edtVenueName, edtVenueCode, edtRadius;
    MaterialButton btnAddGps;
    RecyclerView recyclerGps;
    FusedLocationProviderClient fusedLocationClient;
    double latitude = 0.0;
    double longitude = 0.0;
    DatabaseReference gpsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gps);

        txtlat = findViewById(R.id.txtlat);
        txtlong = findViewById(R.id.txtlong);
        edtVenueName = findViewById(R.id.edtVenueName);
        edtVenueCode = findViewById(R.id.edtVenueCode);
        edtRadius = findViewById(R.id.edtRadius);
        btnAddGps = findViewById(R.id.btnAddGps);
        recyclerGps = findViewById(R.id.recyclerGps);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        gpsRef = FirebaseDatabase.getInstance("https://attendanceapp-bb425-default-rtdb.europe-west1.firebasedatabase.app/").getReference("GPSVenues");
        getCurrentLocation();
        btnAddGps.setOnClickListener(v -> saveVenue());

    }
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100
            );
            return;
        }


        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        txtlat.setText("Lat: " + latitude);
                        txtlong.setText("Long: " + longitude);
                    } else {
                        Toast.makeText(this,
                                "Could not get location",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }







    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
    }

    private void saveVenue() {
        String venueName = edtVenueName.getText().toString().trim();
        String venueCode = edtVenueCode.getText().toString().trim();
        String radius = edtRadius.getText().toString().trim();


        if (venueName.isEmpty() || venueCode.isEmpty() || radius.isEmpty()) {


            Toast.makeText(this,
                    "Fill all fields",
                    Toast.LENGTH_SHORT).show();


            return;
        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("venueName", venueName);
        map.put("venueCode", venueCode);
        map.put("radius", radius);
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        // GPSVenues -> venueCode
        gpsRef.child(venueCode)
                .setValue(map)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this,
                            "GPS Venue Saved",
                            Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this,
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
    }





}