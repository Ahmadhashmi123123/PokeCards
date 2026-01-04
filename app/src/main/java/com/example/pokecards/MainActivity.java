package com.example.pokecards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView forgetPassword, signUp;
    Button btnLogin;
    EditText emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forgetPassword = findViewById(R.id.forgetPassword);
        signUp = findViewById(R.id.signUp);
        btnLogin = findViewById(R.id.btnLogin);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, signup.class);
            startActivity(intent);
        });

        forgetPassword.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, forgetPassword.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String savedEmail = prefs.getString("email", "admin@gmail.com");
        String savedPassword = prefs.getString("password", "admin");

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.equals(savedEmail) && password.equals(savedPassword)) {
            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
