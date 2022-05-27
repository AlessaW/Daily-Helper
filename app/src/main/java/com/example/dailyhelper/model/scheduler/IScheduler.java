package com.example.dailyhelper.model.scheduler;

import android.util.Log;

import com.example.dailyhelper.taskManager.Task;

import java.util.List;

public interface IScheduler {

    List<Task> scheduleTasks();
}
