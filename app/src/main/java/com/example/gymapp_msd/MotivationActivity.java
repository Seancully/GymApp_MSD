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
import android.widget.TextView;

public class MotivationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);

        // Setting up the title with SpannableString for color formatting
        TextView title = findViewById(R.id.titleHealthHarbor);
        String text = "HealthHarbor";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Health" in orange
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // "Harbor" in black
        title.setText(spannableString);


        // For the quotes to be selected
        Button quoteButton1 = findViewById(R.id.quoteButton1);
        quoteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Believe you can and you're halfway there.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton2 = findViewById(R.id.quoteButton2);
        quoteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Keep moving forward.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton3 = findViewById(R.id.quoteButton3);
        quoteButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Stay positive, work hard.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton4 = findViewById(R.id.quoteButton4);
        quoteButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Dream big, work harder.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton5 = findViewById(R.id.quoteButton5);
        quoteButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Be stronger than your excuses.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton6 = findViewById(R.id.quoteButton6);
        quoteButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Progress, not perfection.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton7 = findViewById(R.id.quoteButton7);
        quoteButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~You can do it!~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton8 = findViewById(R.id.quoteButton8);
        quoteButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Stay focused and never give up.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton9 = findViewById(R.id.quoteButton9);
        quoteButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Every day is a fresh start.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton10 = findViewById(R.id.quoteButton10);
        quoteButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Chase goals, not dreams.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton11 = findViewById(R.id.quoteButton11);
        quoteButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Small steps, big changes.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        Button quoteButton12 = findViewById(R.id.quoteButton12);
        quoteButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", "~Be your own hero.~");
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });

        // onclick for reset button
        Button resetButton = findViewById(R.id.resetQuotesButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, MainActivity.class);
                intent.putExtra("selectedQuote", ""); // Sending empty string to reset
                startActivity(intent);
                finish(); // Optional: Close this activity
            }
        });


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle back button action
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}