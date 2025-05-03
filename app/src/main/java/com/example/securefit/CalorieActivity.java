package com.example.securefit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalorieActivity extends AppCompatActivity {

    int totalCalories = 1200;
    int breakfast = 175;
    int lunch = 300;
    int snacks = 175;
    int dinner = 151;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diets); // already exists

        int eaten = breakfast + lunch + snacks + dinner;
        int remaining = totalCalories - eaten;

        ((TextView) findViewById(R.id.tvTotal)).setText("Total: " + totalCalories);
        ((TextView) findViewById(R.id.tvEaten)).setText("Eaten: " + eaten);
        ((TextView) findViewById(R.id.tvRemaining)).setText("Remaining: " + remaining);

        // To add charts, you can integrate MPAndroidChart next
    }
}
