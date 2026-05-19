package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnforgot,btnlogin,btnbackaccount;
    EditText txtuseremail,txtuserpassword;
    CheckBox chremember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnforgot = findViewById(R.id.btnforgot);
        btnlogin = findViewById(R.id.btnlogin);
        btnbackaccount = findViewById(R.id.btnbackaccount);
        txtuseremail = findViewById(R.id.txtuseremail);
        txtuserpassword = findViewById(R.id.txtuserpassword);
        chremember = findViewById(R.id.chremember);

        // event to open create account
        btnbackaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
        // end of create account  event
        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this,Reset.class);
                startActivity(intent);
                finish();
            }
        });
    }
}