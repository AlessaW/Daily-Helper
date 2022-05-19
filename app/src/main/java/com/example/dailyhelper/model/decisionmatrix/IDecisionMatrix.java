package com.example.dailyhelper.model.decisionmatrix;

import java.util.List;
import android.util.Log;

interface IDecisionMatrix {

    void makeValueColumn(String name, List<Integer> values);
    void makeCategoryColumn (List<String> categories);
    void changeColumnName(int column, String name);
    void nameCategory(String name);
    void fillField(int column, int row, int value);
    boolean isInverted(int row);
    void invertValue(int column, int row);

}
