package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taskmaster.Adaptor.TaskAdapter;
import com.example.taskmaster.Models.TaskModel;
import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        List<TaskModel> taskModels=new ArrayList<TaskModel>();

        taskModels.add(new TaskModel("football","It is a famous sport","very good"));
        taskModels.add(new TaskModel("hockey","It is a freezing sport"," good"));
        taskModels.add(new TaskModel("gds","It is a freezing sport"," nice"));


        RecyclerView recyclerView=findViewById(R.id.TaskDetailView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(taskModels));




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



    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        TextView myTask=findViewById(R.id.Tasks);
        String userTask= sharedPreferences.getString("userName","hello user");
        myTask.setText(userTask+" Tasks");
    }

}