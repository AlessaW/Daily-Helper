package com.example.dailyhelper.model.decisionmatrix;

import java.util.List;

public class DecisionMatrix6xN implements IDecisionMatrix{

    private List<DecisionMatrixColumn> columns;


    /**
     * creates a new column for options
     * @param name
     * @param values
     */
    @Override
    public void makeValueColumn(String name, List<Integer> values) {
        columns.add(new DecisionMatrixColumn(name, values));
    }

    /**
     * replaces the category column, which is the first column in the table.
     * TODO: has to be created in constructor
     */
    @Override
    public void makeCategoryColumn(List<String> categories) {
        columns.set(0, new DecisionMatrixColumn(categories));
    }


    @Override
    public void changeColumnName(int column, String name) {

    }

    //Todo: get column list and change value with method from DecisionMatrixColumn (setName?/setValue?)
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
