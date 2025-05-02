package com.example.securefit;
public class Diet {
    private String name;
    private int calories;
    private float rating;
    private int imageResId;

    public Diet(String name, int calories, float rating, int imageResId) {
        this.name = name;
        this.calories = calories;
        this.rating = rating;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public float getRating() {
        return rating;
    }

    public int getImageResId() {
        return imageResId;
    }
}
