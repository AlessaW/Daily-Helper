package com.example.dailyhelper.model.scheduler;

import android.util.Log;

import com.example.dailyhelper.taskManagerDataBase.AppDatabase;
import com.example.dailyhelper.taskManagerDataBase.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */

public class SimpleScheduler implements IScheduler {

    private AppDatabase appDatabase;
    private List<Task> taskList;
    //the time the tasks in the taskList take
    private int listTime;
    private List<PrioList> prioMatrix = new ArrayList<PrioList>(5);
    List<Task> randoms = new ArrayList<>();

    private double probabilityFactor;




    SimpleScheduler(AppDatabase appDatabase,){
        this.appDatabase = appDatabase;
        this.taskList = appDatabase.TaskDao().getAllTasks();
        this.probabilityFactor = 1.35;
        this.listTime = 0;

        prioMatrix.add(createPrioList(1));
        prioMatrix.add(createPrioList(2));
        prioMatrix.add(createPrioList(3));
        prioMatrix.add(createPrioList(4));
        prioMatrix.add(createPrioList(5));
        Log.d("SimpleScheduler", "SimpleScheduler created");
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

        while(listTime != time && randoms.size() != 0){
            randomIndex = (int) (Math.random()*randoms.size()-1);
            Task currentTask = randoms.get(randomIndex);
            if(time-listTime >= currentTask.getDuration()){
                result.add(currentTask);
            }
            listTime+= currentTask.getDuration();
            removeFromRandoms(currentTask);
        }

        Log.d("SimpleScheduler", "finished scheduling");
        return result;

    }

    private void removeFromRandoms(Object o){
        boolean notAll = true;
        while (notAll){
            notAll = randoms.remove(o);
        }
    }


    private PrioList createPrioList(int prio){
        return new PrioList(taskList.stream().filter(t -> t.getPriority() == prio).collect(Collectors.toList()),
                prio, 0);
    }

    private boolean isTooSmall(int length,int compareLength){
        return length <= Math.round(compareLength*probabilityFactor);
    }


    /**
     * sets compareLength to length of lower prio list if compareLength is zero
     * @param prio
     * @param compareLength length of List with prio-1
     * @return
     */
    private int setCompareLength(int prio, int compareLength){
        int result;
        if(compareLength == 0){
            switch (prio){
                case 3: result = prioMatrix.get(0).getLengthInRandomsList();
                        result = setCompareLength(prio-1, result);
                case 4: result = prioMatrix.get(1).getLengthInRandomsList();
                    result = setCompareLength(prio-1, result);
                case 5: result = prioMatrix.get(2).getLengthInRandomsList();
                    result = setCompareLength(prio-1, result);
                default: result = 0;

            }
            return result;
        }else {
            return compareLength;
        }
    }

    private void addIfTooSmall(int prio){
        int compareLength = prioMatrix.get(prio-2).lengthInRandomsList;
        compareLength = setCompareLength(prio, compareLength);

        PrioList prioList = prioMatrix.get(prio-1);
        int prioLength = prioList.getLengthInRandomsList();

        if(prioMatrix.get(prio-1).getLengthInRandomsList() != 0 && compareLength != 0){
            int i = prioList.getPrioList().size()-1;
            while (isTooSmall(prioLength, compareLength)){
                randoms.add(prioList.getPrioList().get(i));

                if(i<=0){
                    i = prioList.getPrioList().size()-1;
                }else i--;

                prioLength++;
            }
        }
    }

    private void fillRandoms(){
        for (int i = 0; i < 5; i++) {
            randoms.addAll(prioMatrix.get(i).prioList);
            prioMatrix.get(i).setLengthInRandomsList(prioMatrix.get(i).getPrioList().size());
        }

        for (int i = 2; i < 6; i++) {
            addIfTooSmall(i);
        }
    }

}