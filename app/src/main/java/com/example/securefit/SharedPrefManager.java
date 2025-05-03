package com.example.securefit;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String PREF_NAME = "SecureFitPrefs";
    private static final String KEY_IS_PROFILE_SETUP_DONE = "isProfileSetupDone";

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

    // New keys
    private static final String KEY_GOAL_CALORIES = "goal_calories";
    private static final String KEY_GOAL_WEIGHT = "goal_weight";
    private static final String KEY_DIET_PLAN = "goal_diet_plan";

    // Save and retrieve calorie goal
    public void setGoalCalories(int calories) {
        editor.putInt(KEY_GOAL_CALORIES, calories);
        editor.apply();
    }
    public int getGoalCalories() {
        return sharedPreferences.getInt(KEY_GOAL_CALORIES, 0);
    }

    // Save and retrieve weight
    public void setGoalWeight(String weight) {
        editor.putString(KEY_GOAL_WEIGHT, weight);
        editor.apply();
    }
    public String getGoalWeight() {
        return sharedPreferences.getString(KEY_GOAL_WEIGHT, "Not set");
    }

    // Save and retrieve diet plan
    public void setDietPlan(String plan) {
        editor.putString(KEY_DIET_PLAN, plan);
        editor.apply();
    }
    public String getDietPlan() {
        return sharedPreferences.getString(KEY_DIET_PLAN, "Not set");
    }

}
