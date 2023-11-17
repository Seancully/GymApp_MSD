package com.example.gymapp_msd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {
    private List<FoodItem> foodItems;
    private OnItemDeleteListener deleteListener;



    private int name;
    float calories;

    public interface OnItemDeleteListener {
        void onDelete(FoodItem item);
    }


    public FoodItemAdapter(List<FoodItem> foodItems, OnItemDeleteListener listener) {
        this.foodItems = foodItems;
        this.deleteListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodItem item = foodItems.get(position);
        holder.foodName.setText(item.name);
        holder.calories.setText(item.calories + " calories");
        holder.deleteButton.setOnClickListener(v -> {
            deleteListener.onDelete(item);
            foodItems.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName, calories;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            calories = itemView.findViewById(R.id.calories);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
