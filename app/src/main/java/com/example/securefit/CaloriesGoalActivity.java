package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.NumberPicker;
import androidx.appcompat.app.AppCompatActivity;

public class CaloriesGoalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_goal);

        NumberPicker picker = findViewById(R.id.caloriesPicker);
        picker.setMinValue(1000);
        picker.setMaxValue(1400);
        picker.setValue(1200);
        picker.setWrapSelectorWheel(false);

        findViewById(R.id.btnNext).setOnClickListener(view ->
                startActivity(new Intent(CaloriesGoalActivity.this, DietPlanActivity.class))
        );
    }
}
