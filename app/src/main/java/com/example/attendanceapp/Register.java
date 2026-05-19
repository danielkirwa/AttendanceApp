package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    Button btnbacklogin,btncreate;
    EditText txtfname,txtlname,txtemail,txtpassword,txtconfirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnbacklogin = findViewById(R.id.btnbacklogin);
        btncreate = findViewById(R.id.btncreate);
        txtfname = findViewById(R.id.txtfname);
        txtlname = findViewById(R.id.txtlname);
        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        txtconfirmpassword = findViewById(R.id.txtconfirmpassword);

        // event to open login
        btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // end of create login

    }
}