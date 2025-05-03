package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FoodQuantityActivity extends AppCompatActivity {

    TextView foodTitle, caloriesInfo;
    Spinner spinnerQuantity, spinnerWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_quantity);

        foodTitle = findViewById(R.id.tvFoodName);
        caloriesInfo = findViewById(R.id.tvCalorieInfo);
        spinnerQuantity = findViewById(R.id.spinnerQuantity);
        spinnerWeight = findViewById(R.id.spinnerWeight);

        String foodName = getIntent().getStringExtra("FOOD_NAME");
        int caloriesPerUnit = getIntent().getIntExtra("CALORIES_PER_UNIT", 0);

        foodTitle.setText(foodName);
        caloriesInfo.setText("Per item: " + caloriesPerUnit + " Cal");

        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(
                this, R.array.quantity_options, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(quantityAdapter);

        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(
                this, R.array.weight_options, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeight.setAdapter(weightAdapter);

        spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) spinnerWeight.setSelection(0);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) spinnerQuantity.setSelection(0);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        findViewById(R.id.btnSave).setOnClickListener(view -> {
            int quantity = 0;
            int weight = 0;

            if (spinnerQuantity.getSelectedItemPosition() > 0) {
                quantity = Integer.parseInt(spinnerQuantity.getSelectedItem().toString());
            }

            if (spinnerWeight.getSelectedItemPosition() > 0) {
                weight = Integer.parseInt(spinnerWeight.getSelectedItem().toString());
            }

            int totalCalories = 0;
            if (quantity > 0) {
                totalCalories = quantity * caloriesPerUnit;
            } else if (weight > 0) {
                totalCalories = (int) ((weight / 100.0f) * caloriesPerUnit);
            }

            Intent result = new Intent();
            result.putExtra("FOOD_NAME", foodName);
            result.putExtra("CALORIES", totalCalories);
            setResult(RESULT_OK, result);
            finish();
        });

        findViewById(R.id.btnReset).setOnClickListener(view -> {
            spinnerQuantity.setSelection(0);
            spinnerWeight.setSelection(0);
        });
    }
}
