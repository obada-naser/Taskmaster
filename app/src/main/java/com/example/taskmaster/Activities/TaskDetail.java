package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.services.s3.util.Mimetypes;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;
import com.example.taskmaster.R;

import java.io.File;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String body=intent.getStringExtra("body");
        String status=intent.getStringExtra("status");
        String key=intent.getStringExtra("key");

        TextView titleDetail=findViewById(R.id.titleDetail);
        titleDetail.setText(title);
        TextView bodyDetail=findViewById(R.id.bodyDetail);
        bodyDetail.setText(body);
        TextView statusDetail=findViewById(R.id.statusDetail);
        statusDetail.setText(status);


        TextView location = findViewById(R.id.location);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(TaskDetail.this);
        String locationData = sharedPreferences.getString(key,"No Location Found");

        location.setText(locationData);


        ImageView image=findViewById(R.id.imageView);
//
//        Amplify.Storage.downloadFile(
//                getIntent().getExtras().get("Image"),
//                new File(getApplicationContext().getFilesDir() + "/download.txt"),
//                result ->{  result.}
//
//
//
//        );
















    }
}