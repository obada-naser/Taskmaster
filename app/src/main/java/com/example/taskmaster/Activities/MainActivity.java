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
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskModel;
import com.example.taskmaster.Adaptor.TaskAdapter;
import com.example.taskmaster.Models.TaskModel1;
import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        List<TaskModel1> taskModelsArray=new ArrayList<>();
        RecyclerView recyclerView=findViewById(R.id.TaskDetailView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(taskModelsArray));


        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });




        Amplify.API.query(
                ModelQuery.list(TaskModel.class),

                response -> {
                    for (TaskModel taskModels:response.getData()){
                        TaskModel1 taskModel1=new TaskModel1(taskModels.getTitle(), taskModels.getBody(), taskModels.getStatus());
                        Log.i("hereisthetitle", taskModels.getTitle());

                        taskModelsArray.add(taskModel1);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );





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
    public void TaskDetail(View view) {
        Intent intent = new Intent(MainActivity.this, TaskDetail.class);
        Button TaskDetail = findViewById(R.id.TaskDetails);
        String TaskDetailsName=TaskDetail.getText().toString();
        intent.putExtra("TaskDetail",TaskDetailsName);
        startActivity(intent);
    }



    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        TextView myTask=findViewById(R.id.Tasks);
        String userTask= sharedPreferences.getString("userName","hello user");
        myTask.setText(userTask+" Tasks");
    }

}