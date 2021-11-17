package com.example.taskmaster.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskModel {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "body")
    public String body;
    @ColumnInfo(name = "status")
    public String status;

    public TaskModel(String title, String body, String status) {
        this.title = title;
        this.body = body;
        this.status = status;
    }
}
