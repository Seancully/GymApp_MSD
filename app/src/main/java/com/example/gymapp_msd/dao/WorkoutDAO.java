package com.example.gymapp_msd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.gymapp_msd.entities.WorkoutEntity;
import java.util.List;

@Dao
public interface WorkoutDAO {
    @Insert
    long insertWorkout(WorkoutEntity workout);

    @Query("SELECT * FROM WorkoutEntity")
    List<WorkoutEntity> getAllWorkouts();

    @Delete
    void deleteWorkout(WorkoutEntity workout);
}
