package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class DietPlanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        RadioGroup radioGroup = findViewById(R.id.dietRadioGroup);

        findViewById(R.id.btnNext).setOnClickListener(view -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String plan = "Not set";
            if (selectedId != -1) {
                plan = ((RadioButton) findViewById(selectedId)).getText().toString();
            }

            new SharedPrefManager(this).setDietPlan(plan);
            startActivity(new Intent(this, FinalSetupActivity.class));
        });
    }
}
