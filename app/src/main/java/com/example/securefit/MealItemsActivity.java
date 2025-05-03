package com.example.securefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MealItemsActivity extends AppCompatActivity {

    private MealItemAdapter adapter;
    private SharedPrefManager prefManager;
    private String mealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_items);

        mealType = getIntent().getStringExtra("MEAL_TYPE");
        prefManager = new SharedPrefManager(this);

        TextView titleText = findViewById(R.id.mealTitle);
        titleText.setText(mealType + " calories");

        RecyclerView recyclerView = findViewById(R.id.recyclerMealItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<MealItem> items = getMealItemsForType(mealType);
        restoreSelections(items);

        adapter = new MealItemAdapter(items);
        recyclerView.setAdapter(adapter);

        SearchView searchView = findViewById(R.id.searchView);
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

        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(v -> {
            adapter.resetSelection();
            prefManager.setMealCalories(mealType, 0);
            prefManager.saveMealSelections(mealType, new ArrayList<>());
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            int total = adapter.calculateTotalCalories();
            prefManager.setMealCalories(mealType, total);
            prefManager.saveMealSelections(mealType, adapter.getSelectedItems());
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            String foodName = data.getStringExtra("FOOD_NAME");
            int totalCalories = data.getIntExtra("CALORIES", 0);
            adapter.markItemSelected(foodName, totalCalories);
        }
    }

    private void restoreSelections(List<MealItem> items) {
        List<MealItem> saved = prefManager.getMealSelections(mealType);
        for (MealItem item : items) {
            for (MealItem savedItem : saved) {
                if (item.name.equalsIgnoreCase(savedItem.name)) {
                    item.isSelected = true;
                    item.totalCalories = savedItem.totalCalories;
                    break;
                }
            }
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
