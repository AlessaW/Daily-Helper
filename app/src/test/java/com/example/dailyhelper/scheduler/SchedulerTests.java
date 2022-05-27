package com.example.dailyhelper.scheduler;

import android.util.Log;

import com.example.dailyhelper.taskManager.Task;
import com.example.dailyhelper.taskManager.TaskCategory;
import com.example.dailyhelper.taskManager.TaskList;

import java.util.ArrayList;
import java.util.List;

public class SchedulerTests {

    Task task1 = new Task("task1", TaskCategory.GROCERY, "", 60, 3);
    Task task2 = new Task("task2", TaskCategory.NATURE, "enjoying a walk in the park", 120, 4);

    TaskList list = new TaskList(new ArrayList<Task>());


}
