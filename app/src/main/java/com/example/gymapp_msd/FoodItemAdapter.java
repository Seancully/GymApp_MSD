package com.example.gymapp_msd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter class for the RecyclerView used in CalorieActivity
public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {
    private List<FoodItem> foodItems; // List of food items
    private OnItemDeleteListener deleteListener; // Listener for delete action

    // Interface to handle deletion of food items
    public interface OnItemDeleteListener {
        void onDelete(FoodItem item);
    }

    // Constructor for the adapter
    public FoodItemAdapter(List<FoodItem> foodItems, OnItemDeleteListener listener) {
        this.foodItems = foodItems;
        this.deleteListener = listener;
    }

    // Creates new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    // Replaces the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodItem item = foodItems.get(position);
        holder.foodName.setText(item.name);
        holder.calories.setText(item.calories + " calories");
        // Set a click listener for the delete button
        holder.deleteButton.setOnClickListener(v -> {
            deleteListener.onDelete(item);
            foodItems.remove(position);
            notifyItemRemoved(position);
        });
    }

    // Returns the size of the food items list
    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    // Provides a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName, calories; // Text views for food name and calories
        public Button deleteButton; // Button to delete a food item

        // Constructor for the ViewHolder, referencing UI components
        public ViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            calories = itemView.findViewById(R.id.calories);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
