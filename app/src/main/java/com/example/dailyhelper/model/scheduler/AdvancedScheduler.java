package com.example.dailyhelper.model.scheduler;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvancedScheduler extends SimpleScheduler {

    TaskCategory taskCategory;

    int priority;

    //0 not sorted, 1 shortTasksFirst, 2 LongTasksFirst
    int sortedAs;


    public AdvancedScheduler(AppDatabase appDatabase, TaskCategory taskCategory, int priority, int sortedAs) {
        super(appDatabase);
        this.taskCategory = taskCategory;
        this.priority = priority;
        this.sortedAs = sortedAs;
    }


    private void filterByCategory(TaskCategory category){
        taskList = taskList.stream().filter(i -> i.getCategory() == category).collect(Collectors.toList());
    }

    private void filterByPriority(int priority){
        taskList = taskList.stream().filter(i -> i.getPriority() == priority).collect(Collectors.toList());
    }





    private List<Task> sortByLength(List<Task> list, int sortedAs){
        if(sortedAs == 2){
            list = list.stream().sorted(Comparator.comparingInt(Task::getDuration).reversed()).collect(Collectors.toList());
        }else if(sortedAs == 1){
            list = list.stream().sorted(Comparator.comparingInt(Task::getDuration)).collect(Collectors.toList());
        }
        return list;
    }

    public List<Task> scheduleTasks(int time, List<Task> tasks){

        this.taskList = tasks;

        if (taskCategory != null){
            filterByCategory(taskCategory);
        }

        if (priority <=SimpleScheduler.NUMBER_OF_PRIOS && priority>=0){
            filterByPriority(priority);
        }

        super.scheduleTasks(time, taskList);

        taskList = sortByLength(taskList, sortedAs);
        return taskList;
    }
}

