package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FinalSetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_setup);

        // Find the button by ID and set the click listener
        findViewById(R.id.btnGoToHome).setOnClickListener(view -> {
            // Mark profile setup as complete in shared preferences
            new SharedPrefManager(this).setProfileSetupDone(true);

            // Navigate to the HomeActivity
            startActivity(new Intent(FinalSetupActivity.this, HomeActivity.class));
            finish();  // Optional: Close the current activity
        });
    }
}
