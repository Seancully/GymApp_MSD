package com.example.gymapp_msd.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gymapp_msd.entities.WorkoutEntity;

import java.util.List;

@Dao
public interface WorkoutDAO {
    @Insert
    void insert(WorkoutEntity workout);

    @Query("SELECT * FROM WorkoutEntity")
    List<WorkoutEntity> getAll();

}

