package com.example.securefit;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;


import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    PieChart pieChart;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        pieChart = findViewById(R.id.pieChart);
        barChart = findViewById(R.id.barChart);

        SharedPrefManager pref = new SharedPrefManager(this);

        int total = pref.getGoalCalories();
        int breakfast = pref.getMealCalories("BREAKFAST");
        int lunch = pref.getMealCalories("LUNCH");
        int snacks = pref.getMealCalories("SNACKS");
        int dinner = pref.getMealCalories("DINNER");
        int notConsumed = Math.max(0, total - (breakfast + lunch + snacks + dinner));

        setupPieChart(breakfast, lunch, snacks, dinner, notConsumed);
        setupBarChart(breakfast, lunch, snacks, dinner, notConsumed);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.diets);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.diets) {
                return true;
            } else if (id == R.id.settings) {
                startActivity(new Intent(this, LogFoodActivity.class));
                return true;
            } else if (id == R.id.profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            } else if (id == R.id.workouts) {
                startActivity(new Intent(this, ChallengesActivity.class));
                return true;
            }
            return false;
        });
    }

    private void setupPieChart(int b, int l, int s, int d, int n) {
        List<PieEntry> entries = new ArrayList<>();
        if (b > 0) entries.add(new PieEntry(b, "Breakfast"));
        if (l > 0) entries.add(new PieEntry(l, "Lunch"));
        if (s > 0) entries.add(new PieEntry(s, "Snacks"));
        if (d > 0) entries.add(new PieEntry(d, "Dinner"));
        if (n > 0) entries.add(new PieEntry(n, "Remaining"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(
                Color.parseColor("#FFA726"),  // Orange
                Color.parseColor("#66BB6A"),  // Green
                Color.parseColor("#42A5F5"),  // Blue
                Color.parseColor("#AB47BC"),  // Purple
                Color.LTGRAY
        );
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(14f);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getLegend().setEnabled(true);
        pieChart.setDescription(new Description());
        pieChart.invalidate();
    }

    private void setupBarChart(int b, int l, int s, int d, int n) {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, b));
        entries.add(new BarEntry(1, l));
        entries.add(new BarEntry(2, s));
        entries.add(new BarEntry(3, d));
        entries.add(new BarEntry(4, n));

        BarDataSet dataSet = new BarDataSet(entries, "Calories");
        dataSet.setColors(
                Color.parseColor("#FFA726"),
                Color.parseColor("#66BB6A"),
                Color.parseColor("#42A5F5"),
                Color.parseColor("#AB47BC"),
                Color.LTGRAY
        );
        BarData data = new BarData(dataSet);
        data.setValueTextSize(12f);
        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setGranularity(50f);
        barChart.setFitBars(true);

        String[] labels = {"Breakfast", "Lunch", "Snacks", "Dinner", "Remaining"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-30);
        xAxis.setDrawGridLines(false);

        barChart.invalidate();
    }
}
