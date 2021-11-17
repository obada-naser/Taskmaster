package com.example.taskmaster.Activities;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmaster.R;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

//        TextView showSubmission=findViewById(R.id.submitted);
//        showSubmission.setText("Submission");

        Button showSubmission=findViewById(R.id.addTask);
        showSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
            }
        });
    }
}