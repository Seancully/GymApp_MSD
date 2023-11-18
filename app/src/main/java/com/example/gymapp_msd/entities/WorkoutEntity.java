package com.example.gymapp_msd.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WorkoutEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String date; // Format: YYYY-MM-DD
    // Other fields like workout name, etc.
}

