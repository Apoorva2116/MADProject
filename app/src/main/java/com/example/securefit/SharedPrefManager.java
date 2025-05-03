package com.example.securefit;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {
    private static final String PREF_NAME = "SecureFitPrefs";
    private static final String KEY_IS_PROFILE_SETUP_DONE = "isProfileSetupDone";
    private static final String KEY_GOAL_CALORIES = "goal_calories";
    private static final String KEY_GOAL_WEIGHT = "goal_weight";
    private static final String KEY_DIET_PLAN = "goal_diet_plan";

    private static final String KEY_BREAKFAST = "calories_breakfast";
    private static final String KEY_LUNCH = "calories_lunch";
    private static final String KEY_SNACKS = "calories_snacks";
    private static final String KEY_DINNER = "calories_dinner";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setProfileSetupDone(boolean isDone) {
        editor.putBoolean(KEY_IS_PROFILE_SETUP_DONE, isDone);
        editor.apply();
    }

    public boolean isProfileSetupDone() {
        return sharedPreferences.getBoolean(KEY_IS_PROFILE_SETUP_DONE, false);
    }

    public void setGoalCalories(int calories) {
        editor.putInt(KEY_GOAL_CALORIES, calories);
        editor.apply();
    }

    public int getGoalCalories() {
        return sharedPreferences.getInt(KEY_GOAL_CALORIES, 0);
    }

    public void setGoalWeight(String weight) {
        editor.putString(KEY_GOAL_WEIGHT, weight);
        editor.apply();
    }

    public String getGoalWeight() {
        return sharedPreferences.getString(KEY_GOAL_WEIGHT, "Not set");
    }

    public void setDietPlan(String plan) {
        editor.putString(KEY_DIET_PLAN, plan);
        editor.apply();
    }

    public String getDietPlan() {
        return sharedPreferences.getString(KEY_DIET_PLAN, "Not set");
    }

    public void setMealCalories(String mealType, int calories) {
        switch (mealType.toUpperCase()) {
            case "BREAKFAST":
                editor.putInt(KEY_BREAKFAST, calories);
                break;
            case "LUNCH":
                editor.putInt(KEY_LUNCH, calories);
                break;
            case "SNACKS":
                editor.putInt(KEY_SNACKS, calories);
                break;
            case "DINNER":
                editor.putInt(KEY_DINNER, calories);
                break;
        }
        editor.apply();
    }

    public int getMealCalories(String mealType) {
        switch (mealType.toUpperCase()) {
            case "BREAKFAST":
                return sharedPreferences.getInt(KEY_BREAKFAST, 0);
            case "LUNCH":
                return sharedPreferences.getInt(KEY_LUNCH, 0);
            case "SNACKS":
                return sharedPreferences.getInt(KEY_SNACKS, 0);
            case "DINNER":
                return sharedPreferences.getInt(KEY_DINNER, 0);
            default:
                return 0;
        }
    }

    public void resetAllMeals() {
        editor.putInt(KEY_BREAKFAST, 0);
        editor.putInt(KEY_LUNCH, 0);
        editor.putInt(KEY_SNACKS, 0);
        editor.putInt(KEY_DINNER, 0);
        editor.remove("meal_selections_BREAKFAST");
        editor.remove("meal_selections_LUNCH");
        editor.remove("meal_selections_SNACKS");
        editor.remove("meal_selections_DINNER");
        editor.apply();
    }

    public void resetMealSelection(String mealType) {
        editor.remove("meal_selections_" + mealType.toUpperCase());
        editor.apply();
    }

    public void saveMealSelections(String mealType, List<MealItem> selectedItems) {
        Gson gson = new Gson();
        String json = gson.toJson(selectedItems);
        editor.putString("meal_selections_" + mealType.toUpperCase(), json);
        editor.apply();
    }

    public List<MealItem> getMealSelections(String mealType) {
        String json = sharedPreferences.getString("meal_selections_" + mealType.toUpperCase(), "");
        if (!json.isEmpty()) {
            Gson gson = new Gson();
            return gson.fromJson(json, new TypeToken<List<MealItem>>() {}.getType());
        }
        return new ArrayList<>();
    }
}
