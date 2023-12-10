package com.example.gymapp_msd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

// Activity class for displaying motivational quotes
public class MotivationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);

        // Setup title with color formatting using SpannableString
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(spannableString);

        // Back button to return to the main activity
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(MotivationActivity.this, MainActivity.class));
            finish();
        });

        // Setup buttons for selecting motivational quotes
        // Each button sends a different quote back to MainActivity
        setupQuoteButton(R.id.quoteButton1, "~Believe you can and you're halfway there.~");
        setupQuoteButton(R.id.quoteButton2, "~Keep moving forward.~");
        setupQuoteButton(R.id.quoteButton3, "~Stay positive, work hard.~");
        setupQuoteButton(R.id.quoteButton4, "~Dream big, work harder.~");
        setupQuoteButton(R.id.quoteButton5, "~Be stronger than your excuses.~");
        setupQuoteButton(R.id.quoteButton6, "~Progress, not perfection.~");
        setupQuoteButton(R.id.quoteButton7, "~You can do it!~");
        setupQuoteButton(R.id.quoteButton8, "~Stay focused and never give up.~");
        setupQuoteButton(R.id.quoteButton9, "~Every day is a fresh start.~");
        setupQuoteButton(R.id.quoteButton10, "~Chase goals, not dreams.~");
        setupQuoteButton(R.id.quoteButton11, "~Small steps, big changes.~");
        setupQuoteButton(R.id.quoteButton12, "~Be your own hero.~");


        // Reset button to clear the selected quote
        Button resetButton = findViewById(R.id.resetQuotesButton);
        resetButton.setOnClickListener(v -> {
            Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
            intent.putExtra("selectedQuote", "");
            startActivity(intent);
            finish();
        });
    }

    // Helper method to setup quote selection buttons
    private void setupQuoteButton(int buttonId, String quote) {
        Button quoteButton = findViewById(buttonId);
        quoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
            intent.putExtra("selectedQuote", quote);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
