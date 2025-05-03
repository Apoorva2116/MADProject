package com.example.securefit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MealDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        ImageView detailImage = findViewById(R.id.detailImage);
        TextView detailName = findViewById(R.id.detailName);
        TextView detailCalories = findViewById(R.id.detailCalories);
        TextView detailRating = findViewById(R.id.detailRating);
        TextView detailTime = findViewById(R.id.detailTime);
        TextView descriptionText = findViewById(R.id.descriptionText);
        Button seeRecipeBtn = findViewById(R.id.seeRecipeButton);

        // Get values from intent
        String name = getIntent().getStringExtra("name");
        int calories = getIntent().getIntExtra("calories", 0);
        float rating = getIntent().getFloatExtra("rating", 0f);
        int imageResId = getIntent().getIntExtra("image", R.drawable.food);

        // Set values
        detailImage.setImageResource(imageResId);
        detailName.setText(name);
        detailCalories.setText(calories + " Calories");
        detailRating.setText("⭐ " + rating);
        detailTime.setText("⏱️ 27 minutes");

        descriptionText.setText(
                name + " is a nutrient-rich meal that's great for energy and overall health. " +
                        "It helps with digestion, provides essential vitamins, and fits perfectly in a fitness-based diet plan."
        );

        // Placeholder action
        seeRecipeBtn.setOnClickListener(v -> {
            // future: show full recipe page
        });
    }
}
