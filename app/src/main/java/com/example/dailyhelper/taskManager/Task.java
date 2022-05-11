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

    public Integer getId() {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
