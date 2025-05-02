package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import androidx.appcompat.app.AppCompatActivity;

public class CurrentWeightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weight);

        NumberPicker picker = findViewById(R.id.weightPicker);
        picker.setMinValue(58);
        picker.setMaxValue(62);
        picker.setValue(60);

        findViewById(R.id.btnNext).setOnClickListener(view ->
                startActivity(new Intent(CurrentWeightActivity.this, GoalWeightActivity.class))
        );
    }
}
