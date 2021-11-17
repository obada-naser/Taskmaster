package com.example.taskmaster;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.taskmaster.Models.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {


    @Query("SELECT * FROM taskmodel")
    List<TaskModel> getAll();


//    @Query("SELECT * FROM taskmodel WHERE uid IN (:taskIds)")
//    List<TaskModel> loadAllByIds(int[] taskIds);

    @Insert
    void insertAll(TaskModel...taskModels);




}
