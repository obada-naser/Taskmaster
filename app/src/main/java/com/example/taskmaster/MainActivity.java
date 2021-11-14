package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void addTask(View view) {
        Intent intent = new Intent(MainActivity.this, AddTask.class);
        startActivity(intent);
    }

    public void allTasks(View view) {
        Intent intent = new Intent(MainActivity.this, AllTasks.class);
        startActivity(intent);
    }
}