package com.example.gymapp_msd.database;

import com.example.gymapp_msd.dao.WorkoutDAO;
import com.example.gymapp_msd.dao.ExerciseDAO;
import com.example.gymapp_msd.dao.ExerciseDetailsDAO;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gymapp_msd.entities.ExerciseDetailEntity;
import com.example.gymapp_msd.entities.ExerciseEntity;
import com.example.gymapp_msd.entities.WorkoutEntity;

@Database(entities = {WorkoutEntity.class, ExerciseEntity.class, ExerciseDetailEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkoutDAO workoutDao();
    public abstract ExerciseDAO exerciseDao();
    public abstract ExerciseDetailsDAO exerciseDetailDao();
    // Other DAOs
}

