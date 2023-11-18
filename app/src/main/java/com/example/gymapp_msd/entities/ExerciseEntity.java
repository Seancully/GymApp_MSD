package com.example.gymapp_msd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(foreignKeys = @ForeignKey(entity = WorkoutEntity.class,
        parentColumns = "id",
        childColumns = "workoutId",
        onDelete = ForeignKey.CASCADE))
public class ExerciseEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int workoutId;
    public String name; // Exercise name
    public float weight;
    public int sets;
    public int reps;
}

