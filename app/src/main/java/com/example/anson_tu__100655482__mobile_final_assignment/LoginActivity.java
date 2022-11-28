package com.example.anson_tu__100655482__mobile_final_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button signInButton1, goToSignUpPageButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signInButton1 = findViewById(R.id.signInButton1);
        goToSignUpPageButton = findViewById(R.id.goToSignUpPageButton);
        DB = new DBHelper(this);

        signInButton1.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUsernamePassword = DB.checkUsernamePassword(user, pass);
                if (checkUsernamePassword) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    User currentUser = (User) getApplicationContext();
                    currentUser.setUsername(user);
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        goToSignUpPageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}