package com.example.dailyhelper.scheduler;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.scheduler.IScheduler;
import com.example.dailyhelper.model.scheduler.SchedulerFactory;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(AndroidJUnit4.class)
public class schedulerTests {

    @Test
    public void testSimpleScheduler(){

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        AppDatabase appDatabase = AppDatabase.getInMemoryDbInstance(appContext);


        List<Task> list = new ArrayList<Task>();
        list.add(new Task("Playing Winx Game", TaskCategory.GAMING, "Having Fun playing", 180, 1));
        list.add(new Task("Se3 Project", TaskCategory.UNIVERSITY, "Creating Tests", 60, 2));
        list.add(new Task("Another Task", TaskCategory.FRIENDS, "", 30, 3));
        list.add(new Task("Task 4", TaskCategory.GROCERY, "", 30, 4));


        IScheduler scheduler = SchedulerFactory.createScheduler(appDatabase, null, 0, 0);


        List<Task> resultList = scheduler.scheduleTasks(120, list);

        String result = list.stream()
                .map(i -> i.getName())
                .collect(Collectors.joining(","));

        Log.i("test", result);

        Assert.assertNotNull(list.get(0));
        Assert.assertEquals(
                list.get(1),
                resultList.get(resultList.indexOf(list.get(1))));

        Assert.assertTrue(resultList.contains(list.get(1)));
        Assert.assertTrue(resultList.contains(list.get(2)));
        Assert.assertTrue(resultList.contains(list.get(3)));
        Assert.assertFalse(resultList.contains(list.get(0)));
    }

    @Test
    public void testAdvancedScheduler(){

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        AppDatabase appDatabase = AppDatabase.getInMemoryDbInstance(appContext);


        List<Task> list = new ArrayList<Task>();
        list.add(new Task("Playing Winx Game", TaskCategory.GAMING, "Having Fun playing", 180, 1));
        list.add(new Task("Se3 Project", TaskCategory.UNIVERSITY, "Creating Tests", 60, 2));
        list.add(new Task("Another Task", TaskCategory.FRIENDS, "", 30, 3));
        list.add(new Task("Task 4", TaskCategory.GROCERY, "", 30, 4));


        IScheduler scheduler = SchedulerFactory.createScheduler(appDatabase, TaskCategory.FRIENDS, 0, 0);
        IScheduler scheduler2 = SchedulerFactory.createScheduler(appDatabase, null, 4, 0);
        IScheduler scheduler3 = SchedulerFactory.createScheduler(appDatabase, null, 0, 2);


        List<Task> resultList = scheduler.scheduleTasks(120, list);
        List<Task> resultList2 = scheduler2.scheduleTasks(120, list);
        List<Task> resultList3 = scheduler3.scheduleTasks(120, list);

        String result = list.stream()
                .map(i -> i.getName())
                .collect(Collectors.joining(","));

        Log.i("test", result);

        Assert.assertNotNull(list.get(0));

        String result1 = resultList.stream()
                .map(i -> i.getName())
                .collect(Collectors.joining(","));

        Log.i("", result1);

        Log.i("Result3: ", resultList3.stream()
                .map(i -> i.getName())
                .collect(Collectors.joining(",")));

        Assert.assertTrue(resultList.contains(list.get(2)));
        Assert.assertTrue(resultList2.contains(list.get(3)));
        Assert.assertEquals(list.get(1), resultList3.get(0));




    }

}
