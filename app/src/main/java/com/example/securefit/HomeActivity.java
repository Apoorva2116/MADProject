package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private String userName = "Apoorva";

    List<Diet> mostViewedDiets;
    List<Diet> personalizedDiets;
    List<Diet> latestDiets;
    DietAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView greetingText = findViewById(R.id.greetingText);
        greetingText.setText("Hi, " + userName + " 👋");

        TextView subGreetingText = findViewById(R.id.subGreetingText);
        subGreetingText.setText("I hope we work out!");

        RecyclerView dietRecyclerView = findViewById(R.id.dietRecyclerView);
        dietRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup sample data
        mostViewedDiets = new ArrayList<>();
        mostViewedDiets.add(new Diet("Vertical Diet", 900, 4.8f, R.drawable.foodimage6));
        mostViewedDiets.add(new Diet("High Protein Meal", 500, 4.6f, R.drawable.foodimage3));

        personalizedDiets = new ArrayList<>();
        personalizedDiets.add(new Diet("Keto Blast", 600, 4.3f, R.drawable.foodimage4));
        personalizedDiets.add(new Diet("Custom Low Carb", 550, 4.5f, R.drawable.foodimage));

        latestDiets = new ArrayList<>();
        latestDiets.add(new Diet("Vegan Boost", 400, 4.7f, R.drawable.foodimage5));
        latestDiets.add(new Diet("Fresh Start", 450, 4.6f, R.drawable.foodimage2));

        adapter = new DietAdapter(this, mostViewedDiets);
        dietRecyclerView.setAdapter(adapter);

        Button tabMostViewed = findViewById(R.id.tabMostViewed);
        Button tabPersonalized = findViewById(R.id.tabPersonalized);
        Button tabLatest = findViewById(R.id.tabLatest);

        tabMostViewed.setOnClickListener(v -> {
            adapter.updateDietList(mostViewedDiets);
            updateTabStyles(tabMostViewed, tabPersonalized, tabLatest);
        });

        tabPersonalized.setOnClickListener(v -> {
            adapter.updateDietList(personalizedDiets);
            updateTabStyles(tabPersonalized, tabMostViewed, tabLatest);
        });

        tabLatest.setOnClickListener(v -> {
            adapter.updateDietList(latestDiets);
            updateTabStyles(tabLatest, tabMostViewed, tabPersonalized);
        });

        updateTabStyles(tabMostViewed, tabPersonalized, tabLatest); // Set default active tab

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.settings) {
                startActivity(new Intent(this, LogFoodActivity.class));
            } else if (itemId == R.id.diets) {
                startActivity(new Intent(this, CalorieActivity.class));
            } else if (itemId == R.id.profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (itemId == R.id.workouts) {
                // Updated: open the Challenges & Fun screen
                startActivity(new Intent(this, ChallengesActivity.class));
            }
            return true;
        });
    }

    private void updateTabStyles(Button selected, Button... others) {
        selected.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.black));
        selected.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        for (Button b : others) {
            b.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grayLight));
            b.setTextColor(ContextCompat.getColor(this, R.color.grayDark));
        }
    }
}
