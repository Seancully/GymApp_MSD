package com.example.gymapp_msd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gymapp_msd.database.AppDatabase;
import com.example.gymapp_msd.entities.WorkoutEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Adapter class for displaying workouts in a RecyclerView
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    private List<WorkoutEntity> workouts; // List of workout entities
    private static Gson gson; // Gson instance for JSON parsing

    // Constructor
    public WorkoutAdapter() {
        this.workouts = new ArrayList<>();
        this.gson = new Gson();
    }

    // Update the workout list in the adapter
    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged(); // Notify the adapter of data changes
    }

    // Create new views for RecyclerView items
    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_items, parent, false);
        return new WorkoutViewHolder(view, this);
    }

    // Bind data to the view holder
    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        WorkoutEntity workout = workouts.get(position);
        holder.bind(workout);
    }

    // Get the size of the workout list
    @Override
    public int getItemCount() {
        return workouts.size();
    }

    // Delete a workout from the list and database
    public void deleteWorkout(int position, Context context) {
        WorkoutEntity workout = workouts.get(position);

        // Get the instance of the database
        AppDatabase db = AppDatabase.getInstance(context);

        // Perform the delete operation in a background thread
        new Thread(() -> {
            db.workoutDao().delete(workout); // Delete the workout from the database
        }).start();

        workouts.remove(position);
        notifyItemRemoved(position);
    }

    // ViewHolder class for RecyclerView items
    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        TextView workoutDetailsTextView;
        ImageButton deleteWorkoutButton;
        private final WorkoutAdapter adapter;

        WorkoutViewHolder(View itemView, WorkoutAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            workoutDetailsTextView = itemView.findViewById(R.id.workoutDetailsTextView);
            deleteWorkoutButton = itemView.findViewById(R.id.deleteWorkoutButton);

            // Setup delete button click listener
            deleteWorkoutButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Context context = itemView.getContext();
                    adapter.deleteWorkout(position, context);
                }
            });
        }

        // Bind workout details to the view
        void bind(WorkoutEntity workout) {
            // Reference: The following code is aided by https://abhiandroid.com/programming/json#gsc.tab=0
            // Parse JSON string to get exercise details
            Type exerciseListType = new TypeToken<List<WorkoutEntity.Exercise>>(){}.getType();
            List<WorkoutEntity.Exercise> exercises = gson.fromJson(workout.getWorkoutDetails(), exerciseListType);
            // Reference complete

            // Reference: The following code is aided by https://developer.android.com/reference/java/lang/StringBuilder
            // Format details into a readable string
            StringBuilder detailsBuilder = new StringBuilder();
            if (exercises != null) {
                for (WorkoutEntity.Exercise exercise : exercises) {
                    detailsBuilder.append(exercise.getExerciseName())
                            .append(":\n           Sets: ").append(exercise.getSets())
                            .append(",\n           Reps: ").append(exercise.getReps())
                            .append(",\n           Weight: ").append(exercise.getWeight())
                            .append("kg\n");
                }
            }
            // Reference complete
            workoutDetailsTextView.setText(detailsBuilder.toString().trim());
        }
    }
}
