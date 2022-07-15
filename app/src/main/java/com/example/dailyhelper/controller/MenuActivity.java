package com.example.dailyhelper.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dailyhelper.R;
import com.example.dailyhelper.controller.taskManager.TaskManagerActivity;
import com.example.dailyhelper.model.scheduler.AddFilterActivity;
import com.example.dailyhelper.model.scheduler.SchedulerActivity;

public class MenuActivity extends AppCompatActivity {

    private Button changeToTaskmanagerButton, changeToSchedulerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        changeToTaskmanagerButton = findViewById(R.id.changeToTaskmanagerButton);

        changeToSchedulerButton = findViewById(R.id.changeToSchedulerButton);

        changeToTaskmanagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(TaskManagerActivity.class);
            }
        });


        changeToSchedulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(AddFilterActivity.class);
            }
        });
    }


    private void switchActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
        startActivity(intent);
    }



}