package com.example.dailyhelper.model.taskmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name ="name")
    private String name;
    @ColumnInfo(name ="category")
    private TaskCategory category;
    @ColumnInfo(name ="description")
    private String description;
    @ColumnInfo(name ="duration")
    private int duration;
    @ColumnInfo(name ="priority")
    private int priority;


    public Task( String name, TaskCategory category, String description, int duration, int priority) {

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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "category=" + category +
                '}';
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
