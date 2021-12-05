package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.taskmaster.R;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String body=intent.getStringExtra("body");
        String status=intent.getStringExtra("status");

        TextView titleDetail=findViewById(R.id.titleDetail);
        titleDetail.setText(title);
        TextView bodyDetail=findViewById(R.id.bodyDetail);
        bodyDetail.setText(body);
        TextView statusDetail=findViewById(R.id.statusDetail);
        statusDetail.setText(status);










    }
}