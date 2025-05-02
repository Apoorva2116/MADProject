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
}
