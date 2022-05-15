package com.example.dailyhelper.taskManager;

import com.example.dailyhelper.taskManager.TaskCategory;

public class Task {

    private final int id;
    private String name;
    private TaskCategory category;
    private String description;
    private int duration;
    private int priority;


    public Task(int id, String name, TaskCategory category, String description, int duration, int priority) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.duration = duration;

        if (priority <=1 && priority >=5 ){
            this.priority = priority;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
