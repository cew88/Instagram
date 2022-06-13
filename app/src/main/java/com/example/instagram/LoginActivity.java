package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/*
Login Activity: Login in page compose of edit text fields and a button
 */

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // If the user is already logged in
        // Skip the log in screen and navigate to the main activity
        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        mEtUsername = findViewById(R.id.etUsername);
        mEtPassword = findViewById(R.id.etPassword);
        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Login Button");
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();
                loginUser(username, password);
            }
        });

    }

    public void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error with login", e);
                    Toast.makeText(LoginActivity.this, "Incorrect login!", Toast.LENGTH_SHORT).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}