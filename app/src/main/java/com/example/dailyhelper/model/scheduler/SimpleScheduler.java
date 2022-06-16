package com.example.dailyhelper.model.scheduler;

import android.util.Log;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 *
 *
 * prio 1 is most important
 */

public class SimpleScheduler implements IScheduler {

    private AppDatabase appDatabase;
    private List<Task> taskList;
    //the time the tasks in the taskList take
    private int listTime;
    private List<PrioList> prioMatrix = new ArrayList<PrioList>(NUMBER_OF_PRIOS);
    List<Task> randoms = new ArrayList<>();

    private double probabilityFactor;

    private static int NUMBER_OF_PRIOS = 4;




    public SimpleScheduler(AppDatabase appDatabase){
        this.appDatabase = appDatabase;

        appDatabase.TaskDao().getAllTasks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws Throwable {
                        taskList = tasks;

                        Log.i("Thread Task List"," Processing on Thread " +Thread.currentThread().getName());
                    }
                    public void onError(@NonNull Throwable e) {

                    }
                });

        this.probabilityFactor = 1.35;
        this.listTime = 0;

        for (int i = 0; i < NUMBER_OF_PRIOS; i++) {
            prioMatrix.add(createPrioList(i+1));
        }

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

        fillRandoms();

        int randomIndex;
        List<Task> result = new ArrayList<>();

        Log.d("SimpleScheduler", "started scheduling");

        while(listTime <= time && randoms.size() != 0){
            randomIndex = (int) (Math.random()*randoms.size()-1);
            Task currentTask = randoms.get(randomIndex);
            if(time-listTime >= currentTask.getDuration()){
                result.add(currentTask);
                listTime+= currentTask.getDuration();
            }

            removeAllOfObject(randoms, currentTask);
        }

        Log.d("SimpleScheduler", "finished scheduling");
        return result;

    }

    private void removeAllOfObject(List list, Object o){
        boolean notAll = true;
        while (notAll){
            notAll = list.remove(o);
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
     * @param compareLength length of List with prio+1
     * @return
     */
    private int setCompareLength(int prio, int compareLength){
        int result;
        if(compareLength == 0){
            switch (prio){

                case 2: result = prioMatrix.get(3).getLengthInRandomsList();
                    result = setCompareLength(prio+1, result);
                case 1: result = prioMatrix.get(2).getLengthInRandomsList();
                    result = setCompareLength(prio+1, result);
                default: result = 0;

            }
            return result;
        }else {
            return compareLength;
        }
    }

    private void addIfTooSmall(int prio){
        int compareLength = prioMatrix.get(prio).lengthInRandomsList;
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
        prioMatrix.get(prio-1).setLengthInRandomsList(prioLength);
    }

    private void fillRandoms(){
        for (int i = 0; i < NUMBER_OF_PRIOS; i++) {
            randoms.addAll(prioMatrix.get(i).prioList);
            prioMatrix.get(i).setLengthInRandomsList(prioMatrix.get(i).getPrioList().size());
        }

        for (int i = NUMBER_OF_PRIOS-1; i >= 1; i--) {
            addIfTooSmall(i);
        }
    }

}
