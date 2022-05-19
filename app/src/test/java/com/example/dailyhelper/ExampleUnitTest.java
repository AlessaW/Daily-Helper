package com.example.dailyhelper;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.example.dailyhelper.model.decisionmatrix.DecisionMatrix6xN;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void logging_isWorking() {
        DecisionMatrix6xN m = new DecisionMatrix6xN();
        m.makeValueColumn("test", new ArrayList<>(12));
        Log.println(Log.ASSERT, "", "");
    }
}