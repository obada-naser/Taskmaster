package com.example.taskmaster;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskmaster.Models.TaskModel;

@Database(entities = {TaskModel.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

}
