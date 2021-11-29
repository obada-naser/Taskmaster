package com.example.taskmaster.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.R;

import java.util.HashMap;
import java.util.Map;

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

        Map< String,String> teamList = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {
                    for (Team team : response.getData()) {

                        teamList.put(team.getTeamTitle(),team.getId());

                    }
                },
                error -> Log.e("MasterTask", error.toString(), error)
        );

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        EditText usersName=findViewById(R.id.usersName);


        String userName=usersName.getText().toString();


        edit.putString("userName",userName);

        edit.apply();

        RadioGroup radioGroup = findViewById(R.id.Teams);

        RadioButton radioButton = findViewById(R.id.teamOne1);
        RadioButton radioButton1 = findViewById(R.id.teamTwo2);
        RadioButton radioButton2 = findViewById(R.id.teamThree3);

        int choosenButtonId=radioGroup.getCheckedRadioButtonId();
        RadioButton chosenButton=findViewById(choosenButtonId);

        String teamName=chosenButton.getText().toString();

        edit.putString("teamName",teamName);
        edit.putString("teamId",teamList.get(teamList));
        edit.apply();


//
//        String teamName = "";
//        if (radioButton.isChecked()) {
//            teamName=radioButton.getText().toString();
//            Log.i(TAG, "choosingTeamMethod: " + teamName);
//        } else if (radioButton1.isChecked()) {
//            teamName = radioButton1.getText().toString();
//            Log.i(TAG, "choosingTeamMethod: " + teamName);
//        } else if (radioButton2.isChecked()) {
//            teamName = radioButton2.getText().toString();
//            Log.i(TAG, "choosingTeamMethod: " + teamName);
//
//        } else {
//            teamName = "null";
//            Log.i(TAG, "choosingTeamMethod: " + teamName);
//        }

//        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor edit=sharedPreferences.edit();
//        EditText usersName=findViewById(R.id.usersName);
//
//
//        String userName=usersName.getText().toString();




        startActivity(intent);


    }






}