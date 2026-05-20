package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AdminDashboard extends AppCompatActivity {
    LinearLayout btnopenaddgps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btnopenaddgps = findViewById(R.id.btnopenaddgps) ;

        btnopenaddgps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(AdminDashboard.this,AddGPS.class);
                startActivity(intent);
                finish();
            }
        });

    }
}