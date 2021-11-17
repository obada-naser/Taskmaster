package com.example.taskmaster.Adaptor;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmaster.Activities.TaskDetail;
import com.example.taskmaster.Models.TaskModel;
import com.example.taskmaster.R;



import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List<TaskModel> taskModels=new ArrayList<>();


    public TaskAdapter(List<TaskModel> taskModels) {
        this.taskModels = taskModels;
    }
    // Creating the view holder:

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public TaskModel Task;
        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view);

        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.Task=taskModels.get(position);

        TextView title=holder.itemView.findViewById(R.id.Title);
        TextView body=holder.itemView.findViewById(R.id.Body);
        TextView status=holder.itemView.findViewById(R.id.status);


        title.setText(holder.Task.title);
        body.setText(holder.Task.body);
        status.setText(holder.Task.status);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), TaskDetail.class);
                intent.putExtra("title",holder.Task.title);
                intent.putExtra("body",holder.Task.body);
                intent.putExtra("status",holder.Task.status);
                view.getContext().startActivity(intent);
            }
        });




    }



    @Override
    public int getItemCount() {
        return taskModels.size();
    }


}
