package com.example.gymapp_msd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

// Activity class for managing calorie tracking
public class CalorieActivity extends AppCompatActivity {

    private List<FoodItem> foodItems = new ArrayList<>();
    private FoodItemAdapter adapter;
    private float totalCalories = 0;
    private TextView totalCaloriesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);

        // Initialize UI components
        TextView title = findViewById(R.id.titleHealthHarbor);
        EditText foodNameInput = findViewById(R.id.foodNameInput);
        EditText proteinInput = findViewById(R.id.proteinInput);
        EditText fatInput = findViewById(R.id.fatInput);
        EditText carbsInput = findViewById(R.id.carbsInput);
        Button addFoodButton = findViewById(R.id.addFoodButton);
        RecyclerView foodRecyclerView = findViewById(R.id.foodRecyclerView);
        totalCaloriesText = findViewById(R.id.totalCaloriesText);

        // Configure RecyclerView for displaying food items
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodItemAdapter(foodItems, item -> {
            totalCalories -= item.getCalories();
            updateTotalCaloriesDisplay();
        });
        foodRecyclerView.setAdapter(adapter);

        // Set up back button to return to the main activity
        ImageButton imageButton = findViewById(R.id.backButton2);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(CalorieActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Handle add food button click
        addFoodButton.setOnClickListener(v -> {
            String foodName = foodNameInput.getText().toString().trim();
            if (foodName.isEmpty()) {
                showToast("Please enter a food name.");
                return;
            }

            try {
                // Parse nutrient inputs
                float protein = parseInput(proteinInput, "protein");
                float fat = parseInput(fatInput, "fat");
                float carbs = parseInput(carbsInput, "carbs");

                // Create new food item and update list and UI
                FoodItem foodItem = new FoodItem(foodName, protein, fat, carbs);
                foodItems.add(foodItem);
                totalCalories += foodItem.getCalories();
                adapter.notifyDataSetChanged();
                updateTotalCaloriesDisplay();

                // Clear input fields after addition
                clearInputs(foodNameInput, proteinInput, fatInput, carbsInput);
            } catch (IllegalArgumentException e) {
                showToast(e.getMessage());
            }
        });
    }

    // Method to parse float input from EditText
    private float parseInput(EditText editText, String nutrientName) {
        String inputStr = editText.getText().toString().trim();
        if (inputStr.isEmpty()) {
            throw new IllegalArgumentException("Please enter the amount of " + nutrientName + ".");
        }
        try {
            return Float.parseFloat(inputStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for " + nutrientName + ".");
        }
    }

    // Method to display a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to clear multiple EditText inputs
    private void clearInputs(EditText... inputs) {
        for (EditText input : inputs) {
            input.setText("");
        }
    }

    // Method to update the total calories display
    private void updateTotalCaloriesDisplay() {
        totalCaloriesText.setText("Total Calories: " + totalCalories);
    }

    // Handle options item selected in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
