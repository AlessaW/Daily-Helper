package com.example.dailyhelper.controller.taskManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dailyhelper.R;

public class TaskManagerActivity extends AppCompatActivity {

    private Button addTaskButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        addTaskButton = findViewById(R.id.AddTask);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new AddTaskFragment());
                fragmentTransaction.commit();

                Log.i("TaskMangerActivity" ,"start the AddTaskFragment" );
            }
        });
    }



}