package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText etUsername;

    EditText etPassword;

    EditText etConfirmP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,

        }, 0);

        etUsername = findViewById(R.id.UsernameRegist);
        etPassword = findViewById(R.id.Password);
        etConfirmP = findViewById(R.id.ConfirmPassword);

        Button registerButton = findViewById(R.id.Register);
        Button loginButton= findViewById(R.id.Login);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String Username = etUsername.getText().toString();
                String Password = etPassword.getText().toString();
                String ConfirmP = etConfirmP.getText().toString();

                if (Password.equals(ConfirmP)){ //Return true if the password type in similar with confirm Pass
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", Username);
                    editor.putString("password", Password);
                    editor.putString("confirm password", ConfirmP);
                    editor.apply();

                    String message = String.format("Registration Sucessfully!");

                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent( MainActivity.this, LoginActitvity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActitvity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }
}
