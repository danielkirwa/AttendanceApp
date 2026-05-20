package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StudentDashboard extends AppCompatActivity {

    Button btnLogout, btnMyCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        btnLogout = findViewById(R.id.btnLogout);
        btnMyCourses = findViewById(R.id.btnMyCourses);

        // ================= MY COURSES =================
        btnMyCourses.setOnClickListener(v -> {
            Intent intent = new Intent(StudentDashboard.this, StudentCourses.class);
            startActivity(intent);
        });

        // ================= LOGOUT =================
        btnLogout.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();

            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            prefs.edit().clear().apply();

            Intent intent = new Intent(StudentDashboard.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            finish();
        });
    }
}