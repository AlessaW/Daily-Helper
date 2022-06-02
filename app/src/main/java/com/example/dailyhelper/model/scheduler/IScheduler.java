package com.example.dailyhelper.model.scheduler;


import com.example.dailyhelper.taskManagerDataBase.Task;
import com.example.dailyhelper.taskManagerDataBase.TaskDao;

import java.util.List;

public interface IScheduler {

    List<Task> scheduleTasks(int time);
}
