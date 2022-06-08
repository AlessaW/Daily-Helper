package com.example.dailyhelper.model.scheduler;

import com.example.dailyhelper.taskManagerDataBase.Task;

import java.util.List;

/**
 * A list that saves the prio value as an int
 */

public class PrioList {

    List<Task> prioList;
    int prio;
    int lengthInRandomsList;

    public int getLengthInRandomsList() {
        return lengthInRandomsList;
    }

    public void setLengthInRandomsList(int lengthInRandomsList) {
        this.lengthInRandomsList = lengthInRandomsList;
    }

    public PrioList(List<Task> prioList, int prio, int lengthInRandomsList) {
        this.prioList = prioList;
        this.prio = prio;
        this.lengthInRandomsList = lengthInRandomsList;
    }

    public List<Task> getPrioList() {
        return prioList;
    }

    public int getPrio() {
        return prio;
    }
}
