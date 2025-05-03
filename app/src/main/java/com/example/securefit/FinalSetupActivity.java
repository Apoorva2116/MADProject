package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class FinalSetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_setup);

        findViewById(R.id.btnGoToHome).setOnClickListener(view -> {
            new SharedPrefManager(this).setProfileSetupDone(true);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }
}
