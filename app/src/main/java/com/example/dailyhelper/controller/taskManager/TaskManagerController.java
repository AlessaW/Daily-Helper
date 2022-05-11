package com.example.dailyhelper.controller.taskManager;

import com.example.dailyhelper.taskManager.Task;
import com.example.dailyhelper.taskManager.TaskCategory;

public class TaskManagerController {

    //CRUD-CREATE
    public Task createTask(Integer id, String name, TaskCategory category, String description, Integer duration, Integer priority){
        Task newTask = new Task(id, name, category, description, duration, priority);

        //Save new Task Object to database

        return newTask;
    }

    //CRUD-UPDATE: OldTask aus TaskList
    public Task editTask(Task oldTask, String name, TaskCategory category, String description, Integer duration, Integer priority){
        Task updatedTask = oldTask;

        if(name != null) {
            updatedTask.setName(name);
        }
        if(category != null) {
            updatedTask.setCategory(category);
        }
        if(description != null) {
            updatedTask.setDescription(description);
        }
        if(duration != null) {
            updatedTask.setDuration(duration);
        }
        if(priority != null) {
            updatedTask.setPriority(priority);
        }

        //Save new Task Object to database

        return updatedTask;
    }

    //CRUD-DELETE
    public Task deleteTask(Integer id) {

        //delete Task with matching ID from database

        Task deletedTask = null;
        return deletedTask;
    }


}
