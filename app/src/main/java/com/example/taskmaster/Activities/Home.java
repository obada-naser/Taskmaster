package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.taskmaster.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent=getIntent();
        TextView homeName=findViewById(R.id.HomePage);
        String homeButton=intent.getExtras().get("HomeButton").toString();

        homeName.setText(homeButton);


    }
}