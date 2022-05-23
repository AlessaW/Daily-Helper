package com.example.dailyhelper.controller.taskManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyhelper.R;
import com.example.dailyhelper.taskManagerDataBase.Task;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Task> tasksList;
    Context context;

    public RecyclerViewAdapter(List<Task> tasksList, Context context) {
        this.tasksList = tasksList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_recyclerview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.Name.setText(tasksList.get(position).getName());
        holder.Category.setText(String.valueOf(tasksList.get(position).getCategory()));
        holder.Duration.setText(String.valueOf(tasksList.get(position).getDuration()));
        holder.Priority.setText(String.valueOf(tasksList.get(position).getPriority()));
        holder.Description.setText(tasksList.get(position).getDescription());
    }


    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        TextView Category;
        TextView Duration;
        TextView Priority;
        TextView Description;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.taskListName);
            Category = itemView.findViewById(R.id.taskListCategory);
            Duration = itemView.findViewById(R.id.TaskListDuration);
            Priority = itemView.findViewById(R.id.taskListPriority);
            Description = itemView.findViewById(R.id.taskListDescription);

        }


    }
}
