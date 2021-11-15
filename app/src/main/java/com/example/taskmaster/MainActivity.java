package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    public void SettingsPage(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        Button settingButton = findViewById(R.id.SettingsPages);
        String SettingsName=settingButton.getText().toString();
        intent.putExtra("settingButton",SettingsName);


        startActivity(intent);
    }
    public void TaskDetail(View view) {
        Intent intent = new Intent(MainActivity.this, TaskDetail.class);
        Button TaskDetail = findViewById(R.id.TaskDetails);
        String TaskDetailsName=TaskDetail.getText().toString();
        intent.putExtra("TaskDetail",TaskDetailsName);
        startActivity(intent);
    }

    public void Home(View view) {
        Intent intent = new Intent(MainActivity.this, Home.class);
        Button homePage=findViewById(R.id.Home);
        String homeName=homePage.getText().toString();
        intent.putExtra("HomeButton",homeName);

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        TextView myTask=findViewById(R.id.Tasks);
        String userTask= sharedPreferences.getString("userName","hello user");
        myTask.setText(userTask+" Tasks");
    }

}