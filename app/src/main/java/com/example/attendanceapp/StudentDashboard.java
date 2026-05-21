package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDashboard extends AppCompatActivity {

    Button btnLogout, btnMyCourses,btnMarkAttendance;
    TextView txtdeviceid,txtStatus,txtrealdeviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        btnLogout = findViewById(R.id.btnLogout);
        btnMyCourses = findViewById(R.id.btnMyCourses);
        txtdeviceid = findViewById(R.id.txtdeviceid);
        txtStatus = findViewById(R.id.txtStatus);
        txtrealdeviceID = findViewById(R.id.txtrealdeviceID);
        btnMarkAttendance = findViewById(R.id.btnMarkAttendance);


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

         // ready form database device id

// reference
        DatabaseReference refdeviceid = FirebaseDatabase
                .getInstance("https://attendanceapp-bb425-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("userDetails");

// load device id
        refdeviceid.child(formatedemail).child("deviceId")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String deviceid = snapshot.getValue(String.class);
                            if(deviceid.equals(deviceId)){
                                txtrealdeviceID.setText(deviceid);
                            }else{
                                txtrealdeviceID.setText("Not your device");
                                btnMarkAttendance.setVisibility(View.INVISIBLE);
                            }

                        } else {

                            txtStatus.setText("No device id found");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(StudentDashboard.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });



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