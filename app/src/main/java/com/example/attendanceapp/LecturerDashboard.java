package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import java.util.Calendar;
import android.widget.TextView;

public class LecturerDashboard extends AppCompatActivity {
    TextView edtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_dashboard);


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




    }
}