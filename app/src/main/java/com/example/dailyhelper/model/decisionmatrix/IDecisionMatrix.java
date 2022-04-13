package com.example.dailyhelper.model.decisionmatrix;

import java.util.List;

interface IDecisionMatrix {

    void makeColumn(String name);
    void changeColumnName(int column, String name);
    void nameCategory(String name);
    void fillField(int column, int row, int value);
    boolean isInverted(int row);
    void invertValue(int column, int row);

}
