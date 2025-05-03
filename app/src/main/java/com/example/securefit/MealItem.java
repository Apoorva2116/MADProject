package com.example.securefit;

public class MealItem {
    public String name;
    public int calories;
    public boolean selected;
    public boolean isSelected = false;
    public int totalCalories = 0;

    public MealItem(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }
}
