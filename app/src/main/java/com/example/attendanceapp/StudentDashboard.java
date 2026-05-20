package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class StudentDashboard extends AppCompatActivity {
    Button btnLogout,btnMyCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        btnLogout = findViewById(R.id.btnLogout);
        btnMyCourses = findViewById(R.id.btnMyCourses);

        // get device id
        String deviceId = Settings.Secure.getString(
                getContentResolver(),
                Settings.Secure.ANDROID_ID
        );

        btnMyCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(StudentDashboard.this,StudentCourses.class);
                startActivity(intent);
                finish();
            }
        });


        btnLogout.setOnClickListener(v -> logoutUser());
    }


    private void logoutUser() {

        // Firebase logout
        FirebaseAuth.getInstance().signOut();

        // Clear Remember Me
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.clear(); // removes remember + role
        editor.apply();

        // Go back to login screen
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        finish();
    }
}