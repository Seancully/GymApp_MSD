package com.example.gymapp_msd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(foreignKeys = @ForeignKey(entity = ExerciseEntity.class,
        parentColumns = "id",
        childColumns = "exerciseId",
        onDelete = ForeignKey.CASCADE))
public class ExerciseDetailEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int exerciseId;
    public int sets;
    public int reps;
    public float weight; // Weight for each set
}
