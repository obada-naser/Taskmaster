package com.example.taskmaster.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.taskmaster.AppDatabase;
import com.example.taskmaster.Models.TaskModel;
import com.example.taskmaster.R;
import com.example.taskmaster.TaskDao;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

//        TextView showSubmission=findViewById(R.id.submitted);
//        showSubmission.setText("Submission");





        EditText titleName=findViewById(R.id.titleName);
        EditText bodyName=findViewById(R.id.bodyName);
        EditText statusName=findViewById(R.id.statusName);


        Button showSubmission=findViewById(R.id.addTask);
        showSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getTitle=titleName.getText().toString();
                String getBody=bodyName.getText().toString();
                String getStatus=statusName.getText().toString();




                    Intent intent = new Intent(AddTask.this, MainActivity.class);
                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "database-name").allowMainThreadQueries().build();
                    TaskDao taskDao = db.taskDao();


                    TaskModel taskModel = new TaskModel(getTitle, getBody, getStatus);
                try {
                    taskDao.insertAll(taskModel);
                }
                catch(NullPointerException nullPointerException){
                    System.out.println("this is an error");

                }
                    Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_LONG).show();

                    startActivity(intent);





            }
        });
    }
}