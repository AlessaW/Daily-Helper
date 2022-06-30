package com.example.dailyhelper.model.scheduler;

import android.util.Log;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * prio 1 is most important
 */

public class SimpleScheduler implements IScheduler {

    AppDatabase appDatabase;
    List<Task> taskList;
    //the time the tasks in the taskList take
    int listTime;
    List<PrioList> prioMatrix = new ArrayList<PrioList>(NUMBER_OF_PRIOS);
    List<Task> randoms = new ArrayList<>();

    double probabilityFactor;

    static int NUMBER_OF_PRIOS = 4;




    public SimpleScheduler(AppDatabase appDatabase){
        this.appDatabase = appDatabase;

        //will be initialized in scheduleTasks-Method
        taskList = null;

        probabilityFactor = 1.35;
        listTime = 0;


        Log.d("SimpleScheduler", "SimpleScheduler created");


    }


    void fillPrioMatrix(){
        for (int i = 0; i < NUMBER_OF_PRIOS; i++) {
            prioMatrix.add(createPrioList(i+1));
        }
    }

    /**
     *schedules tasks randomly
     *
     * @param time the amount of time we want to schedule tasks for
     * @return a list of tasks in the order in which they are scheduled, if there are no tasks, which fit into the time, the list will be empty
     */

    public List<Task> scheduleTasks(int time, List<Task> tasks) {


        taskList = tasks;
        fillPrioMatrix();
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

    void removeAllOfObject(List list, Object o){
        boolean notAll = true;
        while (notAll){
            notAll = list.remove(o);
        }
    }


    PrioList createPrioList(int prio){
        return new PrioList(taskList.stream().filter(t -> t.getPriority() == prio).collect(Collectors.toList()),
                prio, 0);
    }

    boolean isTooSmall(int length,int compareLength){
        return length <= Math.round(compareLength*probabilityFactor);
    }


    /**
     * sets compareLength to length of list with less important (higher number) prio if compareLength is zero
     * @param prio
     * @param compareLength length of List with prio+1
     * @return adjusted compareLength
     */
    int setCompareLength(int prio, int compareLength){

        int offset=1;
        while (compareLength == 0 && prio+offset <= NUMBER_OF_PRIOS){
            compareLength = prioMatrix.get(prio+ offset-1).getLengthInRandomsList();
            offset++;
        }
        return compareLength;
    }

    /**
     * adds more Tasks of the given Priority to the randoms-List if the probability is too small
     * @param prio
     */
    void addIfTooSmall(int prio){
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


    /**
     * puts all Tasks into the randoms List in varying quantity according to the probability which they are to be scheduled with, this probability depends on the priority
     */
    void fillRandoms(){
        for (int i = 0; i < NUMBER_OF_PRIOS; i++) {
            randoms.addAll(prioMatrix.get(i).prioList);
            prioMatrix.get(i).setLengthInRandomsList(prioMatrix.get(i).getPrioList().size());
        }

        for (int i = NUMBER_OF_PRIOS-1; i >= 1; i--) {
            addIfTooSmall(i);
        }
    }

}
