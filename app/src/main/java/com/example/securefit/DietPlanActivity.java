package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class DietPlanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        findViewById(R.id.btnNext).setOnClickListener(view ->
                startActivity(new Intent(DietPlanActivity.this, FinalSetupActivity.class))
        );
    }
}
