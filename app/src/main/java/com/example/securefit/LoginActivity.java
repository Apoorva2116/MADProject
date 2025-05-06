package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    DatabaseHelper db;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);
        db = new DatabaseHelper(this);
        sharedPrefManager = new SharedPrefManager(this);

        findViewById(R.id.loginBtn).setOnClickListener(view -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (db.loginUser(user, pass)) {

                if (sharedPrefManager.isProfileSetupDone()) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, ProfileSetupActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.goToSignup).setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        });
    }
}
