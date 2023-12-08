package com.example.gymapp_msd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

@Entity
public class WorkoutEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String workoutName;
    @ColumnInfo(name = "workout_details")
    private String workoutDetails;

    // Default constructor
    public WorkoutEntity() {}

    // Constructor with workout details and name
    public WorkoutEntity(String workoutName, String workoutDetails) {
        this.workoutName = workoutName;
        this.workoutDetails = workoutDetails;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkoutDetails() {
        return workoutDetails;
    }

    public void setWorkoutDetails(String workoutDetails) {
        this.workoutDetails = workoutDetails;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    // Inner class representing an Exercise
    public static class Exercise {
        private String exerciseName;
        private String weight; // e.g., "50 kg"
        private int sets;
        private int reps;

        // Constructor
        public Exercise(String exerciseName, String weight, int sets, int reps) {
            this.exerciseName = exerciseName;
            this.weight = weight;
            this.sets = sets;
            this.reps = reps;
        }

        // Getters and setters
        public String getExerciseName() {
            return exerciseName;
        }

        public void setExerciseName(String exerciseName) {
            this.exerciseName = exerciseName;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public int getSets() {
            return sets;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }

        public int getReps() {
            return reps;
        }

        public void setReps(int reps) {
            this.reps = reps;
        }
    }

    // Method to convert List<Exercise> to JSON string
    public void setExercises(List<Exercise> exercises) {
        Gson gson = new Gson();
        String json = gson.toJson(exercises);
        setWorkoutDetails(json);
    }

    // Method to retrieve List<Exercise> from JSON string
    public List<Exercise> getExercises() {
        Gson gson = new Gson();
        Type exerciseListType = new TypeToken<List<Exercise>>(){}.getType();
        return gson.fromJson(getWorkoutDetails(), exerciseListType);
    }
}
