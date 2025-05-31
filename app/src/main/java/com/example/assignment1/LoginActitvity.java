package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActitvity extends AppCompatActivity {
    EditText etUsername;

    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actitvity);

        etUsername = findViewById(R.id.LoginUsername);
        etPassword = findViewById(R.id.LoginPassword);

        SharedPreferences sharedPreferences =  getSharedPreferences("MyPrefs", Context.MODE_PRIVATE); //This line showing that the uername--
        //-- is being recorded based on the register Username by using the shared-preferences.
        String savedUsername = sharedPreferences.getString("username", "");
        etUsername.setText(savedUsername);

        Button Register = findViewById(R.id.LoginRegisButton);
        Button Login = findViewById(R.id.LoginButton);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String savedUsername = sharedPreferences.getString("username", ""); // Retrieve the saved username
                String savedPassword = sharedPreferences.getString("password", ""); // Retrieve the saved password

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    // Login successful
                    Toast.makeText(LoginActitvity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActitvity.this, NewEvent.class));
                } else {
                    // Login failed
                    Toast.makeText(LoginActitvity.this, "Invalid Password or Username", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActitvity.this, MainActivity.class));
            }
        });




    }
}