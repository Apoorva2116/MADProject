package com.example.securefit;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        String food = getIntent().getStringExtra("food_name");
        ((TextView) findViewById(R.id.foodTitle)).setText(food);

        NumberPicker qtyPicker = findViewById(R.id.qtyPicker);
        qtyPicker.setMinValue(1);
        qtyPicker.setMaxValue(5);
        qtyPicker.setValue(2);
    }
}
