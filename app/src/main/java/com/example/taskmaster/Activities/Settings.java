package com.example.taskmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taskmaster.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent=getIntent();
        TextView settingNameLabel=findViewById(R.id.SettingName);
        String settingName=intent.getExtras().get("settingButton").toString();
        settingNameLabel.setText(settingName);
    }

    public void userName(View view){
        Intent intent=new Intent(Settings.this,MainActivity.class);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        EditText usersName=findViewById(R.id.usersName);


        String userName=usersName.getText().toString();

        edit.putString("userName",userName);
        edit.apply();



        startActivity(intent);


    }



}