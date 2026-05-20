package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.util.Calendar;

import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LecturerDashboard extends AppCompatActivity {
    TextView edtDate;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_dashboard);

        btnLogout = findViewById(R.id.btnLogout);


        // ================ date picker and to show date===========//
        edtDate=findViewById(R.id.edtDate);

        edtDate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    LecturerDashboard.this,
                    (view, year, month, dayOfMonth) -> {

                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                        edtDate.setText(selectedDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        // ================end date picker and to show date===========//

        // ================start of time picker===========//
        TextView txtTime = findViewById(R.id.txtTime);

        txtTime.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    LecturerDashboard.this,
                    (view, hourOfDay, minute) -> {

                        String selectedTime =
                                String.format("%02d:%02d", hourOfDay, minute);

                        txtTime.setText(selectedTime);

                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
            );

            timePickerDialog.show();

        });
        // ================end time picker and to show time===========//



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