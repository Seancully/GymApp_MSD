package com.example.gymapp_msd;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymapp_msd.database.AppDatabase;
import com.example.gymapp_msd.entities.WorkoutEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    private List<WorkoutEntity> workouts;
    private static Gson gson; // Gson instance for JSON parsing

    public WorkoutAdapter() {
        this.workouts = new ArrayList<>();
        this.gson = new Gson();
    }

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged(); // Notify the adapter of data changes
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_items, parent, false);
        return new WorkoutViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        WorkoutEntity workout = workouts.get(position);
        holder.bind(workout);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void deleteWorkout(int position, Context context) {
        WorkoutEntity workout = workouts.get(position);

        // Get the instance of the database
        AppDatabase db = AppDatabase.getInstance(context);

        // Perform the delete operation in a background thread
        new Thread(() -> {
            db.workoutDao().delete(workout); // this line deletes the workout from the database
            // You might need to handle exceptions here, depending on your implementation
        }).start();

        workouts.remove(position);
        notifyItemRemoved(position);
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        TextView workoutNameTextView;
        TextView workoutDetailsTextView;
        ImageButton deleteWorkoutButton;
        private final WorkoutAdapter adapter;

        WorkoutViewHolder(View itemView, WorkoutAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            workoutNameTextView = itemView.findViewById(R.id.workoutNameTextView);
            workoutDetailsTextView = itemView.findViewById(R.id.workoutDetailsTextView);
            deleteWorkoutButton = itemView.findViewById(R.id.deleteWorkoutButton);

            deleteWorkoutButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Context context = itemView.getContext(); // Get context from itemView
                    adapter.deleteWorkout(position, context);
                }
            });
        }

        void bind(WorkoutEntity workout) {
            Log.d("WorkoutAdapter", "Workout Name: " + workout.getWorkoutName());
            workoutNameTextView.setText(workout.getWorkoutName());


            // Parse the JSON string to extract exercise details
            Type exerciseListType = new TypeToken<List<WorkoutEntity.Exercise>>(){}.getType();
            List<WorkoutEntity.Exercise> exercises = gson.fromJson(workout.getWorkoutDetails(), exerciseListType);

            // Use StringBuilder to build a user-friendly string
            StringBuilder detailsBuilder = new StringBuilder();
            if (exercises != null) {
                for (WorkoutEntity.Exercise exercise : exercises) {
                    detailsBuilder.append(exercise.getExerciseName())
                            .append(":\n           Sets: ").append(exercise.getSets())
                            .append(",\n           Reps: ").append(exercise.getReps())
                            .append(",\n           Weight: ").append(exercise.getWeight())
                            .append("kg\n");  // Change to "kg"
                }
            }
            // Set the formatted details text to the TextView
            workoutDetailsTextView.setText(detailsBuilder.toString().trim());
        }

    }
}
