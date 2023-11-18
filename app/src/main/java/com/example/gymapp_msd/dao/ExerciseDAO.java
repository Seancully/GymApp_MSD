package com.example.gymapp_msd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.gymapp_msd.entities.ExerciseEntity;
import java.util.List;

@Dao
public interface ExerciseDAO {
    @Insert
    long insertExercise(ExerciseEntity exercise);

    @Query("SELECT * FROM ExerciseEntity WHERE workoutId = :workoutId")
    LiveData<List<ExerciseEntity>> getExercisesForWorkout(int workoutId);

    @Delete
    void deleteExercise(ExerciseEntity exercise);

}

