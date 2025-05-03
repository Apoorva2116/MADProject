package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;

public class LogFoodActivity extends AppCompatActivity {

    Button btnBreakfast, btnLunch, btnSnacks, btnDinner, btnReset, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_food);

        // Initialize buttons
        btnBreakfast = findViewById(R.id.btnBreakfast);
        btnLunch = findViewById(R.id.btnLunch);
        btnSnacks = findViewById(R.id.btnSnacks);
        btnDinner = findViewById(R.id.btnDinner);
        btnReset = findViewById(R.id.btnReset);
        btnSave = findViewById(R.id.btnSave);

        // Click listeners for meal buttons
        btnBreakfast.setOnClickListener(v -> openMeal("BREAKFAST"));
        btnLunch.setOnClickListener(v -> openMeal("LUNCH"));
        btnSnacks.setOnClickListener(v -> openMeal("SNACKS"));
        btnDinner.setOnClickListener(v -> openMeal("DINNER"));

        btnReset.setOnClickListener(v -> {
            // You can clear selections or reset local state here
        });

        btnSave.setOnClickListener(v -> {
            // Save logic (e.g., to SharedPreferences or database) can go here
        });

        // Bottom Navigation setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.settings); // Highlight LogFood icon

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (itemId == R.id.diets) {
                startActivity(new Intent(this, CalorieActivity.class));
                return true;
            } else if (itemId == R.id.profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            } else if (itemId == R.id.workouts) {
                startActivity(new Intent(this, ChallengesActivity.class));
                return true;
            } else if (itemId == R.id.settings) {
                return true; // Already here
            }
            return false;
        });
    }

    private void openMeal(String mealType) {
        Intent intent = new Intent(this, MealItemsActivity.class);
        intent.putExtra("MEAL_TYPE", mealType); // Pass which meal was clicked
        startActivity(intent);
    }
}
