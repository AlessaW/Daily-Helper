package com.example.dailyhelper.model.taskmanager;

import java.util.Arrays;

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

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

}
