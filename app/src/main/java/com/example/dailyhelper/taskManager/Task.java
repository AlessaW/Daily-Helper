package com.example.dailyhelper.taskManager;

import com.example.dailyhelper.taskManager.TaskCategory;

public class Task {

    private final Integer id;
    private String name;
    private TaskCategory category;
    private String description;
    private Integer duration;
    private Integer priority;


    public Task(Integer id, String name, TaskCategory category, String description, Integer duration, Integer priority) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.priority = priority;
    }


}
