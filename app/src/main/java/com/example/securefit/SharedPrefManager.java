package com.example.securefit;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String PREF_NAME = "SecureFitPrefs";
    private static final String KEY_IS_PROFILE_SETUP_DONE = "isProfileSetupDone";

    private static final String KEY_GOAL_CALORIES = "goal_calories";
    private static final String KEY_GOAL_WEIGHT = "goal_weight";
    private static final String KEY_DIET_PLAN = "goal_diet_plan";

    // ✅ Define meal calorie keys
    private static final String KEY_BREAKFAST = "breakfast_total";
    private static final String KEY_LUNCH = "lunch_total";
    private static final String KEY_SNACKS = "snacks_total";
    private static final String KEY_DINNER = "dinner_total";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Profile setup tracking
    public void setProfileSetupDone(boolean isDone) {
        editor.putBoolean(KEY_IS_PROFILE_SETUP_DONE, isDone);
        editor.apply();
    }

    public boolean isProfileSetupDone() {
        return sharedPreferences.getBoolean(KEY_IS_PROFILE_SETUP_DONE, false);
    }

    // Goal Calories
    public void setGoalCalories(int calories) {
        editor.putInt(KEY_GOAL_CALORIES, calories);
        editor.apply();
    }

    public int getGoalCalories() {
        return sharedPreferences.getInt(KEY_GOAL_CALORIES, 0);
    }

    // Goal Weight
    public void setGoalWeight(String weight) {
        editor.putString(KEY_GOAL_WEIGHT, weight);
        editor.apply();
    }

    public String getGoalWeight() {
        return sharedPreferences.getString(KEY_GOAL_WEIGHT, "Not set");
    }

    // Diet Plan
    public void setDietPlan(String plan) {
        editor.putString(KEY_DIET_PLAN, plan);
        editor.apply();
    }

    public String getDietPlan() {
        return sharedPreferences.getString(KEY_DIET_PLAN, "Not set");
    }

    // Save meal calories
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

    // Retrieve meal calories
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

    // Clear all saved meal calorie values
    public void resetAllMeals() {
        editor.putInt(KEY_BREAKFAST, 0);
        editor.putInt(KEY_LUNCH, 0);
        editor.putInt(KEY_SNACKS, 0);
        editor.putInt(KEY_DINNER, 0);
        editor.apply();
    }
}
