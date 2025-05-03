package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    TextView calorieGoalText, weightGoalText, dietPlanText;
    Button editGoalsButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        calorieGoalText = findViewById(R.id.calorieGoalText);
        weightGoalText = findViewById(R.id.weightGoalText);
        dietPlanText = findViewById(R.id.dietPlanText);
        editGoalsButton = findViewById(R.id.editGoalsButton);
        logoutButton = findViewById(R.id.logoutButton); // NEW

        // Load data from SharedPreferences
        SharedPrefManager prefManager = new SharedPrefManager(this);
        int calories = prefManager.getGoalCalories();
        String weight = prefManager.getGoalWeight();
        String dietPlan = prefManager.getDietPlan();

        // Set values
        calorieGoalText.setText(calories + " kcal");
        weightGoalText.setText(weight);
        dietPlanText.setText(dietPlan);

        // Handle "Edit Goals"
        editGoalsButton.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileSetupActivity.class));
        });

        // Handle "Logout"
        logoutButton.setOnClickListener(v -> {
            // Optional: clear user session if needed
            // prefManager.setProfileSetupDone(false);
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // Optional: prevent returning to profile with back button
        });

        // Setup Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (itemId == R.id.settings) {
                startActivity(new Intent(this, LogFoodActivity.class));
            } else if (itemId == R.id.diets) {
                startActivity(new Intent(this, CalorieActivity.class));
            } else if (itemId == R.id.workouts) {
                startActivity(new Intent(this, ChallengesActivity.class));
            } else if (itemId == R.id.profile) {
                return true;
            }
            return true;
        });
    }
}
