package com.example.dailyhelper.model.scheduler;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

public class SchedulerFactory {

    public SchedulerFactory(){

    }

    /**
     * Takes arguments of scheduling (like category) to evaluate which scheduler to create
     */
    public static IScheduler createScheduler(AppDatabase appDatabase, TaskCategory category, int priority, int sortedAs){

        if (category == null && priority>= SimpleScheduler.NUMBER_OF_PRIOS && priority<=1 && sortedAs==0){
            return new SimpleScheduler(appDatabase);
        }else {
            return new AdvancedScheduler(appDatabase, category, priority, sortedAs);
        }
    }
}
