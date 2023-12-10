package com.example.gymapp_msd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons for different features in the app
        Button MotivationButton = findViewById(R.id.MotivationButton);
        Button WorkoutButton = findViewById(R.id.WorkoutButton);
        Button CalorieButton = findViewById(R.id.CalorieButton);
        Button LiftButton = findViewById(R.id.LiftButton);

        // Check if a motivational quote was passed from another activity
        Intent intent = getIntent();
        String selectedQuote = intent.getStringExtra("selectedQuote");
        TextView quoteTextView = findViewById(R.id.selectedQuoteTextView);
        if (selectedQuote != null) {
            // Display the received quote if present
            quoteTextView.setText(selectedQuote.isEmpty() ? "" : selectedQuote);
        }

        // Set up click listeners for buttons to navigate to respective activities
        MotivationButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MotivationActivity.class)));
        WorkoutButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WorkoutActivity.class)));
        CalorieButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CalorieActivity.class)));
        LiftButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LiftActivity.class)));

        // Setting up the title with SpannableString for color formatting
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        // Apply color to the title parts
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Health" in magenta
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Harbor" in white
        title.setText(spannableString);
    }
}
