package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileSetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        findViewById(R.id.btnStart).setOnClickListener(view ->
                startActivity(new Intent(ProfileSetupActivity.this, CurrentWeightActivity.class))
        );
    }
}
