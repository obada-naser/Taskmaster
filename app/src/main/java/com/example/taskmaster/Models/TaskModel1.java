package com.example.taskmaster.Models;


//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity
public class TaskModel1 {

//    @PrimaryKey(autoGenerate = true)
    public int uid;

//    @ColumnInfo(name = "title")
    public String title;
//
//    @ColumnInfo(name = "body")
    public String body;
//    @ColumnInfo(name = "status")
    public String status;

    public String imageName;

    public String key;



    public TaskModel1(String title, String body, String status,String imageName,String key) {
        this.title = title;
        this.body = body;
        this.status = status;
        this.imageName=imageName;
        this.key=key;

    }
}
