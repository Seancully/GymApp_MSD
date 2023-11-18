package com.example.gymapp_msd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;
import com.example.gymapp_msd.entities.ExerciseDetailEntity;
import java.util.List;

@Dao
public interface ExerciseDetailsDAO {
    @Insert
    long insertExerciseDetail(ExerciseDetailEntity exerciseDetail);

    @Query("SELECT * FROM ExerciseDetailEntity WHERE exerciseId = :exerciseId")
    LiveData<List<ExerciseDetailEntity>> getDetailsForExercise(int exerciseId);

    @Delete
    void deleteExerciseDetail(ExerciseDetailEntity exerciseDetail);
}
