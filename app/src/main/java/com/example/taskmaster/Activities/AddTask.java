package com.example.taskmaster.Activities;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import androidx.core.app.ActivityCompat;
//import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.amplifyframework.datastore.generated.model.Team;
import com.example.taskmaster.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddTask extends AppCompatActivity {

    public String imageName = "";
    public Uri uri;
    Location locationData;

    String location1;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            locationData = location;

                            Geocoder geocoder;
                            List<Address> addresses = new ArrayList<>();
                            geocoder = new Geocoder(AddTask.this, Locale.getDefault());

                            try {
                                addresses = geocoder.getFromLocation(locationData.getLatitude(), locationData.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            String knownName = addresses.get(0).getFeatureName();


                            System.out.println("this the city:" + city);
                            location1=city+" "+country;

                        }
                    }
                });

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

                String teamName=getTeamName();







        Amplify.API.query(
                ModelQuery.get(Team.class,team.get(teamName)),
                response1 -> {

                    String getTitle=titleName.getText().toString();
                    String getBody=bodyName.getText().toString();
                    String getStatus=statusName.getText().toString();

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






//                    TaskModel1 taskModel = new TaskModel1(getTitle, getBody, getStatus);

                    try {

                        TaskModel taskModel = TaskModel.builder().teamId(response1.getData().getId()).title(getTitle).body(getBody).status(getStatus).imageName(imageName).build();


                        String key=taskModel.getId();


                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddTask.this);
                        sharedPreferences.edit().putString(key, location1).apply();




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



//    private void uploadInputStream(imageName){



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        assert data!=null;
        uri=data.getData();
        File file=new File(uri.getPath());
        imageName=file.getName();
        Log.i("TAG25", "onActivityResult: ");

        super.onActivityResult(requestCode, resultCode, data);


    }
}