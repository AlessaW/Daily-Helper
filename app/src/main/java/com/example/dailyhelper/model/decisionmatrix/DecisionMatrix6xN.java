package com.example.dailyhelper.model.decisionmatrix;

import java.util.List;

public class DecisionMatrix6xN implements IDecisionMatrix{

    private List<DecisionMatrixColumn> columns;


    @Override
    public void makeColumn(String name) {

    }

    @Override
    public void changeColumnName(int column, String name) {

    }

    @Override
    public void nameCategory(String name) {

    }

    @Override
    public void fillField(int column, int row, int value) {

    }

    @Override
    public boolean isInverted(int row) {
        return false;
    }

    @Override
    public void invertValue(int column, int row) {

    }
}
