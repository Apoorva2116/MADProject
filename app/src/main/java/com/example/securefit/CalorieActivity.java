package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalorieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diets);

        SharedPrefManager prefManager = new SharedPrefManager(this);

        int totalCalories = prefManager.getGoalCalories();
        int breakfast = prefManager.getMealCalories("BREAKFAST");
        int lunch = prefManager.getMealCalories("LUNCH");
        int snacks = prefManager.getMealCalories("SNACKS");
        int dinner = prefManager.getMealCalories("DINNER");

        int eaten = breakfast + lunch + snacks + dinner;
        int remaining = totalCalories - eaten;

        ((TextView) findViewById(R.id.tvTotal)).setText("Total: " + totalCalories);
        ((TextView) findViewById(R.id.tvEaten)).setText("Eaten: " + eaten);
        ((TextView) findViewById(R.id.tvRemaining)).setText("Remaining: " + remaining);

        ((TextView) findViewById(R.id.tvBreakfast)).setText("BREAKFAST: " + breakfast + " / 255 Cal");
        ((TextView) findViewById(R.id.tvLunch)).setText("LUNCH: " + lunch + " / 319 Cal");
        ((TextView) findViewById(R.id.tvSnacks)).setText("SNACKS: " + snacks + " / 179 Cal");
        ((TextView) findViewById(R.id.tvDinner)).setText("DINNER: " + dinner + " / 447 Cal");

        ImageView graphIcon = findViewById(R.id.graphIcon);
        graphIcon.setOnClickListener(v -> {
            Intent intent = new Intent(CalorieActivity.this, GraphActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.diets);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                startActivity(new Intent(this, HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.diets) {
                return true;
            } else if (id == R.id.settings) {
                startActivity(new Intent(this, LogFoodActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.workouts) {
                startActivity(new Intent(this, ChallengesActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}
