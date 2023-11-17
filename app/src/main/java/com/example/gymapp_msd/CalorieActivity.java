package com.example.gymapp_msd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
            totalCalories -= item.calories;
            updateTotalCaloriesDisplay();
        });
        foodRecyclerView.setAdapter(adapter);

        // Button click listeners
        addFoodButton.setOnClickListener(v -> {
            try {
                String foodName = foodNameInput.getText().toString();
                float protein = Float.parseFloat(proteinInput.getText().toString());
                float fat = Float.parseFloat(fatInput.getText().toString());
                float carbs = Float.parseFloat(carbsInput.getText().toString());

                if (foodName.isEmpty()) {
                    throw new IllegalArgumentException("Food name is required");
                }

                FoodItem foodItem = new FoodItem(foodName, protein, fat, carbs);
                foodItems.add(foodItem);
                totalCalories += foodItem.getCalories(); // Make sure FoodItem has a getCalories method
                adapter.notifyDataSetChanged();
                updateTotalCaloriesDisplay();
            } catch (NumberFormatException e) {
                // Handle parsing error
            } catch (IllegalArgumentException e) {
                // Handle empty input error
            }
        });
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
