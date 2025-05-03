package com.example.securefit;

public class MealItem {
    public String name;
    public int calories;
    public boolean isSelected;
    public int totalCalories;

    public MealItem(String name, int calories) {
        this.name = name;
        this.calories = calories;
        this.isSelected = false;
        this.totalCalories = 0;
    }
}
