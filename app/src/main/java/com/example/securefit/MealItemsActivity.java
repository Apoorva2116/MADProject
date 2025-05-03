package com.example.securefit;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MealItemsActivity extends AppCompatActivity {

    private TextView titleText;
    private RecyclerView recyclerView;
    private MealItemAdapter adapter;
    private List<MealItem> itemList;
    private String mealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_items);

        titleText = findViewById(R.id.mealTitle);
        recyclerView = findViewById(R.id.recyclerMealItems);
        SearchView searchView = findViewById(R.id.searchView);
        Button btnReset = findViewById(R.id.btnReset);
        Button btnSave = findViewById(R.id.btnSave);

        mealType = getIntent().getStringExtra("MEAL_TYPE");
        titleText.setText(mealType + " calories");

        itemList = getMealItemsForType(mealType);
        adapter = new MealItemAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Search filtering
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        // Reset button
        btnReset.setOnClickListener(v -> adapter.resetSelection());

        // Save calories and return
        btnSave.setOnClickListener(v -> {
            int total = adapter.calculateTotalCalories();
            SharedPrefManager manager = new SharedPrefManager(this);
            manager.setMealCalories(mealType, total);
            finish();
        });

        // If returning from FoodQuantityActivity
        if (getIntent().hasExtra("FOOD_NAME") && getIntent().hasExtra("CALORIES")) {
            String foodName = getIntent().getStringExtra("FOOD_NAME");
            int totalCalories = getIntent().getIntExtra("CALORIES", 0);
            adapter.markItemSelected(foodName, totalCalories);  // ✅ You need markItemSelected in MealItemAdapter
        }
    }

    private List<MealItem> getMealItemsForType(String type) {
        List<MealItem> items = new ArrayList<>();
        if ("BREAKFAST".equals(type)) {
            items.add(new MealItem("Poha", 120));
            items.add(new MealItem("Oats", 100));
            items.add(new MealItem("Upma", 150));
            items.add(new MealItem("Idli", 110));
            items.add(new MealItem("Banana", 89));
        } else if ("LUNCH".equals(type)) {
            items.add(new MealItem("Aloo Paratha", 200));
            items.add(new MealItem("Tuna Sandwich", 180));
            items.add(new MealItem("Rice & Dal", 300));
            items.add(new MealItem("Chapati", 80));
            items.add(new MealItem("Paneer Curry", 250));
        } else if ("SNACKS".equals(type)) {
            items.add(new MealItem("Fruit Bowl", 120));
            items.add(new MealItem("Protein Bar", 200));
            items.add(new MealItem("Smoothie", 160));
            items.add(new MealItem("Popcorn", 90));
        } else if ("DINNER".equals(type)) {
            items.add(new MealItem("Grilled Chicken", 280));
            items.add(new MealItem("Salad", 100));
            items.add(new MealItem("Quinoa Bowl", 220));
            items.add(new MealItem("Soup", 150));
        }
        return items;
    }
}
