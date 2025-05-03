package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentWeightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weight);

        NumberPicker picker = findViewById(R.id.weightPicker);
        picker.setMinValue(35);
        picker.setMaxValue(150);
        picker.setValue(60);

        findViewById(R.id.btnNext).setOnClickListener(view -> {
            new SharedPrefManager(this).setGoalWeight(picker.getValue() + " kg");
            startActivity(new Intent(this, GoalWeightActivity.class));
        });
    }
}
