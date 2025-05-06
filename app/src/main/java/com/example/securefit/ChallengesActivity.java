package com.example.securefit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChallengesActivity extends AppCompatActivity {

    ImageView yogaChallengeImage, kimChloeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        yogaChallengeImage = findViewById(R.id.yogaChallengeImage);
        kimChloeImage = findViewById(R.id.kimChloeImage);

        yogaChallengeImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "https://www.youtube.com/watch?v=MBMD_iSiBsg&list=PL7YpEPrqQ3XTm9kj12UrLCMfI4R1JnNkX&index=2"));
            startActivity(intent);
        });

        kimChloeImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "https://www.youtube.com/watch?v=oa2Gf5BGr6Q"));
            startActivity(intent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.workouts);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.settings) {
                startActivity(new Intent(this, LogFoodActivity.class));
            } else if (itemId == R.id.diets) {
                startActivity(new Intent(this, CalorieActivity.class));
            } else if (itemId == R.id.profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (itemId == R.id.home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (itemId == R.id.workouts) {
                return true;
            }
            return true;
        });
    }
}
