package com.example.dailyhelper.model.scheduler;

import android.util.Log;

import com.example.dailyhelper.taskManagerDataBase.AppDatabase;
import com.example.dailyhelper.taskManagerDataBase.Task;
import com.example.dailyhelper.taskManagerDataBase.TaskDao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SimpleScheduler implements IScheduler {

    private AppDatabase appDatabase;
    private List<Task> taskList;
    //the time the tasks in the taskList take
    private int listTime;


    SimpleScheduler(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
        this.taskList = appDatabase.TaskDao().getAllTasks();
        this.listTime = 0;
        Log.d("SimpleScheduluer", "SimpleScheduler created");
    }

    /**
     *schedules tasks randomly
     *
     * @param time the amount of time we want to schedule tasks for
     * @return a list of tasks in the order in which they are scheduled, if there are no tasks, which fit into the time, the list will be empty
     */
    @Override
    public List<Task> scheduleTasks(int time) {

        int randomIndex;
        List<Task> result = new ArrayList<>();

        Log.d("SimpleScheduler", "started scheduling");

        while(listTime != time && taskList.size() != 0){
            randomIndex = (int) (Math.random()*taskList.size()-1);
            Task currentTask = taskList.get(randomIndex);
            if(time-listTime >= currentTask.getDuration()){
                result.add(currentTask);
            }
            taskList.remove(currentTask);
        }

        Log.d("SimpleScheduluer", "finished scheduling");
        return result;

    }


}
