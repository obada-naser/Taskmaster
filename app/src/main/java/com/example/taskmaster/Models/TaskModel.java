package com.example.taskmaster.Models;

public class TaskModel {
    public String title;
    public String body;
    public String status;

    public TaskModel(String title, String body, String status) {
        this.title = title;
        this.body = body;
        this.status = status;
    }
}
