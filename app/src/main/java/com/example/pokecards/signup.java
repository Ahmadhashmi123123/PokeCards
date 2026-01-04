package com.example.pokecards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {

    TextView logIn;
    Button btnSignup, btnReset;
    EditText email, phoneNumber, password, retypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        logIn = findViewById(R.id.logIn);
        btnSignup = findViewById(R.id.btnSignup);
        btnReset = findViewById(R.id.btnReset);

        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retypePassword);

        logIn.setOnClickListener(v -> {
            Intent intent = new Intent(signup.this, MainActivity.class);
            startActivity(intent);
        });

        btnReset.setOnClickListener(v -> {
            email.setText("");
            phoneNumber.setText("");
            password.setText("");
            retypePassword.setText("");
        });

        btnSignup.setOnClickListener(v -> {
            // Optional: Add signup logic here
        });
    }
}
