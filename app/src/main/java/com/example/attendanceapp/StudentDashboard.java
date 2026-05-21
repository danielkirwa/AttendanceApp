package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentDashboard extends AppCompatActivity {

    Button btnLogout, btnMyCourses;
    TextView txtdeviceid,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        btnLogout = findViewById(R.id.btnLogout);
        btnMyCourses = findViewById(R.id.btnMyCourses);
        txtdeviceid = findViewById(R.id.txtdeviceid);
        txtStatus = findViewById(R.id.txtStatus);


        // get device id
        String deviceId = Settings.Secure.getString(
                getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
        // display id
        txtdeviceid.setText(deviceId);

        // read device id of the logged in account from db
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            // no user
        }
        String loggedinuseremail = user.getEmail();
        txtStatus.setText(loggedinuseremail);
        String formatedemail = loggedinuseremail.replace("." , "_")
                .replace("@" , "_at_");




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