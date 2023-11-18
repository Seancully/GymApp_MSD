package com.example.gymapp_msd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

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

        // RecyclerView setup
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodItemAdapter(foodItems, item -> {
            totalCalories -= item.getCalories(); // Updated to use getter method
            updateTotalCaloriesDisplay();
        });
        foodRecyclerView.setAdapter(adapter);

        // Button click listeners
        addFoodButton.setOnClickListener(v -> {
            String foodName = foodNameInput.getText().toString().trim();
            if (foodName.isEmpty()) {
                showToast("Please enter a food name.");
                return;
            }

            try {
                float protein = parseInput(proteinInput, "protein");
                float fat = parseInput(fatInput, "fat");
                float carbs = parseInput(carbsInput, "carbs");

                FoodItem foodItem = new FoodItem(foodName, protein, fat, carbs);
                foodItems.add(foodItem);
                totalCalories += foodItem.getCalories();
                adapter.notifyDataSetChanged();
                updateTotalCaloriesDisplay();

                clearInputs(foodNameInput, proteinInput, fatInput, carbsInput); // Clear input fields
            } catch (IllegalArgumentException e) {
                showToast(e.getMessage());
            }
        });
    }

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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearInputs(EditText... inputs) {
        for (EditText input : inputs) {
            input.setText("");
        }
    }

    private void updateTotalCaloriesDisplay() {
        totalCaloriesText.setText("Total Calories: " + totalCalories);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
