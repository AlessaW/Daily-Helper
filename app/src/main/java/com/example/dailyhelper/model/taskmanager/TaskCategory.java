package com.example.dailyhelper.model.taskmanager;

public enum TaskCategory {
    SPORT("Sport"),
    UNIVERSITY("University"),
    LAUNDRY("Laundry"),
    GROCERY("Grocery"),
    HOUSEHOLD("Household Chores"),
    COOKING("Cooking"),
    GAMING("Gaming"),
    FRIENDS("Friends"),
    NATURE("Nature");

    final String name;

    TaskCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
