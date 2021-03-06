package com.example.taskmaster.Activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        Intent intent = getIntent();
        TextView settingNameLabel = findViewById(R.id.SettingName);
        String settingName = intent.getExtras().get("settingButton").toString();
        settingNameLabel.setText(settingName);



        Map< String,String> listTeam = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {
                    for (Team team : response.getData()) {

                        listTeam.put(team.getTeamTitle(),team.getId());

                    }
                },
                error -> Log.e("MasterTask", error.toString(), error)
        );


        //**************************



        Button saveUserNameButton = findViewById(R.id.saveButton);
        saveUserNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        EditText usersName = findViewById(R.id.usersName);


        String userName = usersName.getText().toString();



        RadioButton radioButton = findViewById(R.id.Team1);
        RadioButton radioButton1 = findViewById(R.id.Team2);
        RadioButton radioButton2 = findViewById(R.id.Team3);


        String teamName1 = radioButton.getText().toString();
        String teamName2 = radioButton1.getText().toString();
        String teamName3 = radioButton2.getText().toString();
        String newName="";
        String id="";
        if (radioButton.isChecked() && listTeam.containsKey(teamName1) ) {
            System.out.println("*********************************** here");
//            edit.putString("teamName", teamName1);
            newName=teamName1;
            id=listTeam.get(teamName1);

//            edit.putString("teamId", listTeam.get(teamName1));
            System.out.println(listTeam.get(teamName1));
            Log.i("TAG1", "choosingTeamMethod: " + teamName1);

        }
         else if (radioButton1.isChecked() && listTeam.containsKey(teamName2)) {
            newName=teamName2;
            id=listTeam.get(teamName2);

//
//            edit.putString("teamId",listTeam.get(teamName2));
//            edit.putString("teamName", teamName2);
            Log.i("TAG2", "choosingTeamMethod: " + teamName2);
        } else if (radioButton2.isChecked() && listTeam.containsKey(teamName3)) {
//            edit.putString("teamId",listTeam.get(teamName3));
//            edit.putString("teamName", teamName3);

            newName=teamName3;
            id=listTeam.get(teamName3);

            Log.i("TAG3", "choosingTeamMethod: " + teamName3);


        }



//        int choosenButtonId=radioGroup.getCheckedRadioButtonId();
//        RadioButton chosenButton=findViewById(choosenButtonId);
//
//        String teamName=chosenButton.getText().toString();
//        listTeam.get(newName)

        edit.putString("teamId",id);
//        System.out.println("****************************************obada");
//        System.out.println(id);

        edit.putString("teamName",newName);


        edit.putString("userName", userName);




//        edit.putString("teamId", "anything");

//        Log.i("id1", "onCreate: "+listTeam.get(listTeam));


        edit.apply();


//        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor edit=sharedPreferences.edit();
//        EditText usersName=findViewById(R.id.usersName);
//
//
//        String userName=usersName.getText().toString();
        Intent goHomePage = new Intent(Settings.this, MainActivity.class);
        startActivity(goHomePage);









            }
        });

    }



}


