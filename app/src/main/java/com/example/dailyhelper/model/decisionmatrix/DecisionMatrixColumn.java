package com.example.dailyhelper.model.decisionmatrix;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 Created by Alessa and Jannika
 */



public class DecisionMatrixColumn<E> {
 //   private static final Logger log = LogManager.getLogger(DecisionMatrixColumn.class);
    private String name;
    private List<E> decisionMatrixColumn;

    DecisionMatrixColumn(String name, List<E> values){
        this.name = name;
        this.decisionMatrixColumn = values;
    }

    DecisionMatrixColumn(List<E> values){
        this.decisionMatrixColumn = values;
    }

    public String getName() {
        return name;
    }

    private int calculate(){
        int sum = 0;
        return sum;
    };

}
