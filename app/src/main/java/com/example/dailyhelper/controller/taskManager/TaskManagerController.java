package com.example.dailyhelper.controller.taskManager;

import com.example.dailyhelper.taskManager.Task;
import com.example.dailyhelper.taskManager.TaskCategory;

public class TaskManagerController {

    public Task createTask(Integer id, String name, TaskCategory category, String description, Integer duration, Integer priority){
        Task newTask = new Task(id, name, category, description, duration, priority);
        return newTask;
    }

    public Task editTask(Task oldTask, String name, TaskCategory category, String description, Integer duration, Integer priority){
        Task updatedTask = oldTask;

        //Update the Task object and save it to database

        return updatedTask;
    }

    public Task deleteTask(Integer id) {

        //delete Task with matching ID from database

        Task deletedTask = null;
        return deletedTask;
    }


}
