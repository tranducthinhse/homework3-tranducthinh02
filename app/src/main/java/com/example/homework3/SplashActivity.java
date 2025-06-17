package com.example.homework3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize views
        ConstraintLayout chalkboardContainer = findViewById(R.id.chalkboard_container);
        TextView chalkboardText = findViewById(R.id.chalkboard_text);
        TextView titleText = findViewById(R.id.title_text);

        // Load animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        // Apply animations
        chalkboardContainer.startAnimation(fadeIn);
        chalkboardText.startAnimation(fadeIn);
        titleText.startAnimation(slideUp);

        // Navigate to MainActivity after delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            // Optional: Add transition animation if you have created the animation files
            // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }, SPLASH_DURATION);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        // Disable back button on splash screen
        // Do nothing
    }
}