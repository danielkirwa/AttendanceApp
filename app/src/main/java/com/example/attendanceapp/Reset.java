package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Reset extends AppCompatActivity {
    Button btnrest;
    EditText txtresetemail;
    TextView btnrestbacklogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        btnrest = findViewById(R.id.btnreset);
        txtresetemail = findViewById(R.id.txtresetemail);
        btnrestbacklogin = findViewById(R.id.btnrestbacklogin);

        // event to open login
        btnrestbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Reset.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // end of create login
    }
}