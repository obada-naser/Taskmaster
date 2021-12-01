package com.example.taskmaster.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.taskmaster.Adaptor.TaskAdapter;
import com.example.taskmaster.Models.TaskModel1;
import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());


            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }




//

//
//        taskModels.add(new TaskModel1("football","It is a famous sport","very good"));
//        taskModels.add(new TaskModel1("hockey","It is a freezing sport"," good"));
//        taskModels.add(new TaskModel1("gds","It is a freezing sport"," nice"));

//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "database-name").allowMainThreadQueries().fallbackToDestructiveMigration().build();
//
//        TaskDao taskDao = db.taskDao();
//        List<TaskModel>  taskModels = taskDao.getAll();

//        Team teamOne = Team.builder().teamTitle("Team1").build();
//        Team teamTwo = Team.builder().teamTitle("Team2").build();
//        Team teamThree = Team.builder().teamTitle("Team3").build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(teamOne),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(teamTwo),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(teamThree),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );





        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String gettingId=sharedPreferences.getString("teamId","");
        System.out.println("***************************************obada");
        System.out.println(gettingId);

//        Log.i("id", "onCreate: "+gettingId);





        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
//
        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );


//        private void uploadFile(){
//            File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");
//
//            try {
//                BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//                writer.append("Example file contents");
//                writer.close();
//            } catch (Exception exception) {
//                Log.e("MyAmplifyApp", "Upload failed", exception);
//            }
//
//            Amplify.Storage.uploadFile(
//                    "ExampleKey",
//                    exampleFile,
//                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//            );
//        }















//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "my@email.com")
//                .build();
//        Amplify.Auth.signUp("username", "Password123", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );











        List<TaskModel1> taskModelsArray=new ArrayList<>();




    if(!gettingId.equals("")) {
        RecyclerView recyclerView = findViewById(R.id.TaskDetailView);


        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        Amplify.API.query(
                ModelQuery.get(Team.class, gettingId),

                response -> {
                    for (TaskModel taskModels : response.getData().getTaskModels()) {

                        TaskModel1 taskModel1 = new TaskModel1(taskModels.getTitle(), taskModels.getBody(), taskModels.getStatus());
                        Log.i("hereisthetitle", taskModels.getTitle());

                        taskModelsArray.add(taskModel1);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(taskModelsArray));
    }

//        Button signOutButton = findViewById(R.id.logOut);
//        signOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Amplify.Auth.signOut(
//                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );
//
//
//            }
//        });














//        System.out.println("****************"+ Arrays.toString(taskModels.toArray())+"************");









    }
    public void addTask(View view) {
        Intent intent = new Intent(MainActivity.this, AddTask.class);

        startActivity(intent);
    }

    public void allTasks(View view) {
        Intent intent = new Intent(MainActivity.this, AllTasks.class);
        startActivity(intent);
    }

    public void SettingsPage(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        Button settingButton = findViewById(R.id.SettingsPages);
        String SettingsName=settingButton.getText().toString();
        intent.putExtra("settingButton",SettingsName);


        startActivity(intent);
    }
    public void logoutUser(View view) {
        Amplify.Auth.signOut(
                () -> Log.i("AuthQuickstart", "Signed out successfully"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }




    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        TextView myTask=findViewById(R.id.Tasks);
        TextView teamTasks=findViewById(R.id.teamTasks);
        String userTask= sharedPreferences.getString("userName","hello user");
        String TeamTasks= sharedPreferences.getString("teamName","hello Team");

        AuthUser authUser=Amplify.Auth.getCurrentUser();

        TextView loggedUser=findViewById(R.id.loggedUser);

        if(authUser !=null) {

            loggedUser.setText(authUser.getUsername());
        }else{
            loggedUser.setText("No one here");
        }



//        TextView teamName=findViewById(R.id.teamTasks);

        myTask.setText(userTask+" Tasks");
        teamTasks.setText(TeamTasks+" Tasks");


    }

}
