package com.example.taskmaster.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity {

    public String imageName = "";
    public Uri uri;

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
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

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
                uploadInputStream();




        Amplify.API.query(
                ModelQuery.get(Team.class,team.get(teamName)),
                response1 -> {

                    String getTitle=titleName.getText().toString();
                    String getBody=bodyName.getText().toString();
                    String getStatus=statusName.getText().toString();






//                    TaskModel1 taskModel = new TaskModel1(getTitle, getBody, getStatus);

                    try {
                        TaskModel taskModel = TaskModel.builder().teamId(response1.getData().getId()).title(getTitle).body(getBody).status(getStatus).imageName(imageName).build();

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


    @SuppressLint("IntentReset")
    public void pickFile(View view){
        @SuppressLint("IntentReset") Intent selectedFile=new Intent(Intent.ACTION_GET_CONTENT);

        selectedFile.setType(("*/"));
            selectedFile=Intent.createChooser(selectedFile,"Select File");
        startActivityForResult(selectedFile,1234);


    }

    private void uploadInputStream(){
        if (uri!=null){
            try{
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Amplify.Storage.uploadInputStream(
                        imageName,
                        inputStream,
                        result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                );

            }  catch (FileNotFoundException error) {
                Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
            }

            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null) {
            uri = data.getData();
            File file = new File(uri.getPath());
            imageName = file.getName();
            Log.i("TAG", "onActivityResult: ");
        }
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_ID_MULTIPLE_PERMISSIONS:
//                if (ContextCompat.checkSelfPermission(MainActivity.this,
//                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(),
//                            "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (ContextCompat.checkSelfPermission(addTask.this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(),
//                            "FlagUp Requires Access to Your Storage.",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    chooseImage(MainActivity.this);
//                }
//                break;
//        }
//    }

}