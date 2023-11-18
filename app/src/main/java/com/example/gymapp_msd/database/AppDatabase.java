package com.example.gymapp_msd.database;

import com.example.gymapp_msd.dao.WorkoutDAO;
import com.example.gymapp_msd.dao.ExerciseDAO;
import com.example.gymapp_msd.dao.ExerciseDetailsDAO;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import android.content.Context;

import com.example.gymapp_msd.entities.ExerciseDetailEntity;
import com.example.gymapp_msd.entities.ExerciseEntity;
import com.example.gymapp_msd.entities.WorkoutEntity;

@Database(entities = {WorkoutEntity.class, ExerciseEntity.class, ExerciseDetailEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkoutDAO workoutDao();
    public abstract ExerciseDAO exerciseDao();
    public abstract ExerciseDetailsDAO exerciseDetailDao();

    // Singleton instance
    private static volatile AppDatabase INSTANCE;

    // Method to get the instance of the database
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "your_database_name")
                            .fallbackToDestructiveMigration() // This will reset the database instead of migrating if no Migration object found
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

