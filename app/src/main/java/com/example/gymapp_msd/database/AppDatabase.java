package com.example.gymapp_msd.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gymapp_msd.dao.WorkoutDAO;
import com.example.gymapp_msd.entities.WorkoutEntity;

// Define the database with Room. Version number should be incremented with any schema changes
@Database(entities = {WorkoutEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE; // Singleton instance of the database

    // Method to get the singleton instance of the database
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                // Double-check if instance is still null before creating a new one
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "AppDatabase.db") // Create database instance
                            .fallbackToDestructiveMigration() // Strategy for handling migrations
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Abstract method for getting DAO
    public abstract WorkoutDAO workoutDao();
}
