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
            items.add(new MealItem("Boiled Eggs", 78));
            items.add(new MealItem("Paneer Sandwich", 210));
            items.add(new MealItem("Peanut Butter Toast", 190));
            items.add(new MealItem("Cornflakes", 130));
            items.add(new MealItem("Paratha", 220));
            items.add(new MealItem("Milkshake", 140));
            items.add(new MealItem("Besan Chilla", 160));
            items.add(new MealItem("Muesli", 150));
            items.add(new MealItem("Dhokla", 115));
            items.add(new MealItem("Sprouts Salad", 100));
            items.add(new MealItem("Pancakes", 190));
            items.add(new MealItem("Wheat Toast", 85));
            items.add(new MealItem("Cheese Omelette", 210));
            items.add(new MealItem("Fruit Salad", 90));
            items.add(new MealItem("Sabudana Khichdi", 180));
        } else if ("LUNCH".equals(type)) {
            items.add(new MealItem("Aloo Paratha", 200));
            items.add(new MealItem("Tuna Sandwich", 180));
            items.add(new MealItem("Rice & Dal", 300));
            items.add(new MealItem("Chapati", 80));
            items.add(new MealItem("Paneer Curry", 250));
            items.add(new MealItem("Mixed Veg", 160));
            items.add(new MealItem("Curd Rice", 230));
            items.add(new MealItem("Chicken Curry", 290));
            items.add(new MealItem("Vegetable Biryani", 320));
            items.add(new MealItem("Rajma Chawal", 310));
            items.add(new MealItem("Matar Paneer", 270));
            items.add(new MealItem("Fish Fry", 260));
            items.add(new MealItem("Egg Curry", 280));
            items.add(new MealItem("Chole Bhature", 420));
            items.add(new MealItem("Veg Pulao", 290));
            items.add(new MealItem("Kadhi Chawal", 250));
            items.add(new MealItem("Bhindi Masala", 180));
            items.add(new MealItem("Daal Makhani", 310));
            items.add(new MealItem("Gobi Aloo", 190));
            items.add(new MealItem("Vegetable Kofta", 270));
        } else if ("SNACKS".equals(type)) {
            items.add(new MealItem("Fruit Bowl", 120));
            items.add(new MealItem("Protein Bar", 200));
            items.add(new MealItem("Smoothie", 160));
            items.add(new MealItem("Popcorn", 90));
            items.add(new MealItem("Nuts Mix", 180));
            items.add(new MealItem("Granola", 150));
            items.add(new MealItem("Tea & Biscuit", 130));
            items.add(new MealItem("Dark Chocolate", 170));
            items.add(new MealItem("Greek Yogurt", 100));
            items.add(new MealItem("Roasted Chickpeas", 110));
            items.add(new MealItem("Samosa", 150));
            items.add(new MealItem("Kachori", 180));
            items.add(new MealItem("Bhel Puri", 140));
            items.add(new MealItem("Fruit Chaat", 120));
            items.add(new MealItem("Murmura Mix", 100));
            items.add(new MealItem("Boiled Corn", 90));
            items.add(new MealItem("Paneer Tikka", 220));
            items.add(new MealItem("Vegetable Roll", 210));
            items.add(new MealItem("Cheese Cubes", 85));
            items.add(new MealItem("Energy Balls", 160));
        } else if ("DINNER".equals(type)) {
            items.add(new MealItem("Grilled Chicken", 280));
            items.add(new MealItem("Salad", 100));
            items.add(new MealItem("Quinoa Bowl", 220));
            items.add(new MealItem("Soup", 150));
            items.add(new MealItem("Vegetable Stir Fry", 180));
            items.add(new MealItem("Fish Curry", 260));
            items.add(new MealItem("Tofu & Rice", 240));
            items.add(new MealItem("Dosa", 200));
            items.add(new MealItem("Roti & Sabzi", 190));
            items.add(new MealItem("Moong Dal Khichdi", 210));
            items.add(new MealItem("Stuffed Capsicum", 170));
            items.add(new MealItem("Pav Bhaji", 350));
            items.add(new MealItem("Grilled Paneer", 230));
            items.add(new MealItem("Veg Cutlet", 180));
            items.add(new MealItem("Lauki Curry", 160));
            items.add(new MealItem("Tomato Soup", 110));
            items.add(new MealItem("Chickpea Salad", 190));
            items.add(new MealItem("Zucchini Noodles", 140));
            items.add(new MealItem("Vegetable Stew", 175));
            items.add(new MealItem("Kadhi Pakora", 220));
        }
        return items;
    }


}

