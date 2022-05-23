package com.example.dailyhelper.controller.taskManager;

import com.example.dailyhelper.taskManagerDataBase.Task;
import com.example.dailyhelper.taskManagerDataBase.TaskCategory;

public class TaskManagerController {

    //CRUD-CREATE
    public Task createTask( String name, TaskCategory category, String description, int duration, int priority){
        Task newTask = new Task( name, category, description, duration, priority);

        //Save new Task Object to database

        return newTask;
    }

    //CRUD-UPDATE: OldTask aus TaskList
    public Task editTask(Task oldTask, String name, TaskCategory category, String description, int duration, int priority){
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
        if(duration != 0) {
            updatedTask.setDuration(duration);
        }
        if(priority != 0) {
            updatedTask.setPriority(priority);
        }

        //Save new Task Object to database

        return updatedTask;
    }

    //CRUD-DELETE
    public Task deleteTask(int id) {

        //delete Task with matching ID from database

        Task deletedTask = null;
        return deletedTask;
    }


}
