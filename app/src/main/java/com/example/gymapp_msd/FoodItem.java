package com.example.gymapp_msd;

public class FoodItem {
    String name;
    float protein;
    float fat;
    float carbs;
    float calories;

    // Constructor
    public FoodItem(String name, float protein, float fat, float carbs) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.calories = calculateCalories(protein, fat, carbs);
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getCalories() {
        return calories;
    }

    // Method to calculate calories
    private float calculateCalories(float protein, float fat, float carbs) {
        return (protein + carbs) * 4 + fat * 9;
    }
}
