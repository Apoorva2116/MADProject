package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    private String userName = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set greeting text
        TextView greetingText = findViewById(R.id.greetingText);
        greetingText.setText("Hi, " + userName + "!");

        // Bottom Navigation Bar setup (fixed to use if-else instead of switch)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.settings) {
                    startActivity(new Intent(HomeActivity.this, LogFoodActivity.class));
                    return true;
                } else if (itemId == R.id.diets) {
                    startActivity(new Intent(HomeActivity.this, CalorieActivity.class));
                    return true;
                } else if (itemId == R.id.home) {
                    // User is already on HomeActivity, do nothing
                    return true;
                } else if (itemId == R.id.profile) {
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    return true;
                } else if (itemId == R.id.workouts) {
                    startActivity(new Intent(HomeActivity.this, WorkoutsActivity.class));
                    return true;
                } else {
                    return false;
                }
            }

        });
        };
    }
