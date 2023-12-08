package com.example.gymapp_msd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gymapp_msd.entities.WorkoutEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    private List<WorkoutEntity> workouts;
    private Gson gson; // Gson instance for JSON parsing

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
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        WorkoutEntity workout = workouts.get(position);
        holder.bind(workout, gson);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

        public void bind(WorkoutEntity workout, Gson gson) {
        }

        static class WorkoutViewHolder extends RecyclerView.ViewHolder {
            TextView workoutDetailsTextView;

            WorkoutViewHolder(View itemView) {
                super(itemView);
                workoutDetailsTextView = itemView.findViewById(R.id.workoutDetailsTextView);
            }

        void bind(WorkoutEntity workout, Gson gson) {
            StringBuilder detailsBuilder = new StringBuilder();
            detailsBuilder.append("Workout: ").append(workout.getWorkoutName()).append("\n");

            // Parsing the JSON string to extract exercise details
            Type exerciseListType = new TypeToken<List<WorkoutEntity.Exercise>>(){}.getType();
            List<WorkoutEntity.Exercise> exercises = gson.fromJson(workout.getWorkoutDetails(), exerciseListType);

            if (exercises != null) {
                for (WorkoutEntity.Exercise exercise : exercises) {
                    detailsBuilder.append("Exercise: ").append(exercise.getExerciseName())
                            .append(", Weight: ").append(exercise.getWeight())
                            .append(", Sets: ").append(exercise.getSets())
                            .append(", Reps: ").append(exercise.getReps())
                            .append("\n");
                }
            }

            workoutDetailsTextView.setText(detailsBuilder.toString());
        }
    }
}
