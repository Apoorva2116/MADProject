package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

public class GoalWeightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_weight);

        NumberPicker picker = findViewById(R.id.goalWeightPicker);
        picker.setMinValue(35);
        picker.setMaxValue(55);
        picker.setValue(150);

        findViewById(R.id.btnNext).setOnClickListener(view -> {
            startActivity(new Intent(this, CaloriesGoalActivity.class));
        });
    }
}
