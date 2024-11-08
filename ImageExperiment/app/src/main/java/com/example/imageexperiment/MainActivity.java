package com.example.imageexperiment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout layout;
    private int currentBackgroundIndex = 0;

    private int[] backgroundImages = {
            R.drawable.background_image1,
            R.drawable.background_image2,
            R.drawable.background_image3,
            R.drawable.background_image4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        layout.setBackgroundResource(backgroundImages[currentBackgroundIndex]);

        Button switchBackgroundButton = findViewById(R.id.switchBackgroundButton);
        switchBackgroundButton.setOnClickListener(v -> {
            // Create fade-out effect for the old background
            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(layout, "alpha", 1f, 0f);
            fadeOut.setDuration(500); // Set fade-out duration to 500ms

            // Create fade-in effect for the new background
            fadeOut.addListener(new android.animation.Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(android.animation.Animator animation) {}

                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    // Update the background once fade-out ends
                    currentBackgroundIndex = (currentBackgroundIndex + 1) % backgroundImages.length;
                    layout.setBackgroundResource(backgroundImages[currentBackgroundIndex]);

                    // Start fade-in effect
                    ObjectAnimator fadeIn = ObjectAnimator.ofFloat(layout, "alpha", 0f, 1f);
                    fadeIn.setDuration(500); // Set fade-in duration to 500ms
                    fadeIn.setInterpolator(new AccelerateDecelerateInterpolator());
                    fadeIn.start();
                }

                @Override
                public void onAnimationCancel(android.animation.Animator animation) {}

                @Override
                public void onAnimationRepeat(android.animation.Animator animation) {}
            });

            fadeOut.start(); // Start the fade-out animation
        });
    }
}
