package com.example.dailyhelper.model.scheduler;

import com.example.dailyhelper.model.taskmanager.Task;

import java.util.List;

public interface IScheduler {

    List<Task> scheduleTasks(int time, List<Task> tasks);
}
