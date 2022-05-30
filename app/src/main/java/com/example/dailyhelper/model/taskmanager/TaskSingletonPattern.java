package com.example.dailyhelper.model.taskmanager;

public class TaskSingletonPattern {

    static TaskSingletonPattern instance;

    static int id;

    public  void setId(int id) {
        TaskSingletonPattern.id = id;
    }

    public int getId() {
        return id;
    }

    static String name;

    static TaskCategory category;

    static String description;

    static int duration;

    static int priority;

    public  void setInstance(TaskSingletonPattern instance) {
        TaskSingletonPattern.instance = instance;
    }

    public  void setName(String name) {
        TaskSingletonPattern.name = name;
    }

    public  void setCategory(TaskCategory category) {
        TaskSingletonPattern.category = category;
    }

    public  void setDescription(String description) {
        TaskSingletonPattern.description = description;
    }

    public  void setDuration(int duration) {
        TaskSingletonPattern.duration = duration;
    }

    public  void setPriority(int priority) {
        TaskSingletonPattern.priority = priority;
    }

    public  String getName() {
        return name;
    }

    public  TaskCategory getCategory() {
        return category;
    }

    public  String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public  int getPriority() {
        return priority;
    }

    public static TaskSingletonPattern getInstance() {

        if (instance == null) {
            instance = new TaskSingletonPattern();
        }
        return instance;
    }

}
