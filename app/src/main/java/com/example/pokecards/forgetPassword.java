package com.example.pokecards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class forgetPassword extends AppCompatActivity {

    EditText passOne, passConfirm;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        passOne = findViewById(R.id.passOne);
        passConfirm = findViewById(R.id.passConfirm);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {
            String newPass = passOne.getText().toString();
            String confirmPass = passConfirm.getText().toString();

            if(newPass.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(forgetPassword.this,"Please fill both fields",Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirmPass)) {
                Toast.makeText(forgetPassword.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("password", newPass);
                editor.apply();

                Toast.makeText(forgetPassword.this,"Password Updated Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(forgetPassword.this, login.class);
                startActivity(intent);
            }
        });
    }
}
