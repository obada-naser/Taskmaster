package com.example.taskmaster.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.R;

import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

//        TextView showSubmission=findViewById(R.id.submitted);
//        showSubmission.setText("Submission");

//
//        try {
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.configure(getApplicationContext());
//            Log.i("TaskMaster1", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("TaskMaster1", "Could not initialize Amplify", error);
//        }
//
//
//




        EditText titleName=findViewById(R.id.titleName);
        EditText bodyName=findViewById(R.id.bodyName);
        EditText statusName=findViewById(R.id.statusName);





        Button showSubmission=findViewById(R.id.addTask);


        Map<String, String> team = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {

                    Log.i("response", response.toString());
                    for (Team Teams : response.getData()) {
                        team.put(Teams.getTeamTitle(), Teams.getId());

                    }
                },error -> Log.e("TaskMaster3", error.toString(), error)

        );


        showSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddTask.this, MainActivity.class);











//                RadioGroup radioGroup=findViewById(R.id.Teams1);
//                int button=radioGroup.getCheckedRadioButtonId();
//                RadioButton choosen=findViewById(button);
//                String choosenTeam=choosen.getText().toString();
                String teamName=getTeamName();




        Amplify.API.query(
                ModelQuery.get(Team.class,team.get(teamName)),
                response1 -> {

                    String getTitle=titleName.getText().toString();
                    String getBody=bodyName.getText().toString();
                    String getStatus=statusName.getText().toString();



//                    TaskModel1 taskModel = new TaskModel1(getTitle, getBody, getStatus);

                    try {
                        TaskModel taskModel = TaskModel.builder().teamId(response1.getData().getId()).title(getTitle).body(getBody).status(getStatus).build();

                        Amplify.API.mutate(
                                ModelMutation.create(taskModel),
                                response -> Log.i("MyAmplifyApp1", "Added Task with id: " + response.getData().getId()),
                                error -> Log.e("MyAmplifyApp1", "Create failed", error)
                        );

                    } catch (NullPointerException nullPointerException) {
                        System.out.println("this is an error");

                    }
                }, error -> Log.e("TaskMaster6", error.toString(), error)

                );
                    Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_LONG).show();

                    startActivity(intent);





            }
        });
    }
    private String getTeamName(){
//        RadioGroup teams=findViewById(R.id.Teams1);

        RadioButton teamOne=findViewById(R.id.Team1);
        RadioButton teamTwo=findViewById(R.id.Team2);
        RadioButton teamThree=findViewById(R.id.Team3);

        String teamName="";

        if(teamOne.isChecked()){
            teamName=teamOne.getText().toString();
        }
        else if (teamTwo.isChecked()){
            teamName=teamTwo.getText().toString();
        }
        else if (teamThree.isChecked()){
            teamName=teamThree.getText().toString();
        }
        else{
            teamName=null;
        }
    return teamName;
    }
//    @SuppressLint("IntentReset")
//    public void pickFile(){
//        @SuppressLint("IntentReset") Intent selectedFile=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        selectedFile.setType(("*/"));
//            selectedFile=Intent.createChooser(selectedFile,"Select File");
//        startActivityForResult(selectedFile,1234);
//
//
//    }


}