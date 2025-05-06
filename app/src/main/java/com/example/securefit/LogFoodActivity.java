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

        btnBreakfast = findViewById(R.id.btnBreakfast);
        btnLunch = findViewById(R.id.btnLunch);
        btnSnacks = findViewById(R.id.btnSnacks);
        btnDinner = findViewById(R.id.btnDinner);
        btnReset = findViewById(R.id.btnReset);
        btnSave = findViewById(R.id.btnSave);

        btnBreakfast.setOnClickListener(v -> openMeal("BREAKFAST"));
        btnLunch.setOnClickListener(v -> openMeal("LUNCH"));
        btnSnacks.setOnClickListener(v -> openMeal("SNACKS"));
        btnDinner.setOnClickListener(v -> openMeal("DINNER"));

        btnReset.setOnClickListener(v -> {
        });

        btnSave.setOnClickListener(v -> {
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.settings);

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
                return true;
            }
            return false;
        });
    }

    private void openMeal(String mealType) {
        Intent intent = new Intent(this, MealItemsActivity.class);
        intent.putExtra("MEAL_TYPE", mealType);
        startActivity(intent);
    }
}
