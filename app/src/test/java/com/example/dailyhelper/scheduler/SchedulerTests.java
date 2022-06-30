package com.example.dailyhelper.scheduler;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.scheduler.IScheduler;
import com.example.dailyhelper.model.scheduler.SimpleScheduler;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchedulerTests {

    @Test
        public void testData(){

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

                AppDatabase appDatabase = AppDatabase.getInMemoryDbInstance(appContext);


                List<Task> list = new ArrayList<Task>();
                list.add(new Task("Playing Winx Game", TaskCategory.GAMING, "Having Fun playing", 180, 1));
                list.add(new Task("Se3 Project", TaskCategory.UNIVERSITY, "Creating Tests", 60, 2));
                list.add(new Task("Another Task", TaskCategory.FRIENDS, "", 30, 3));
                list.add(new Task("Task 4", TaskCategory.GROCERY, "", 30, 4));


                IScheduler scheduler = new SimpleScheduler(appDatabase);


                List<Task> resultList = scheduler.scheduleTasks(120, list);

                String result = list.stream()
                        .map(i -> i.getName())
                        .collect(Collectors.joining(","));

                Log.i("test", result);

                Assert.assertNotNull(list.get(0));
                Assert.assertEquals(
                        new Task("Se3 Project", TaskCategory.UNIVERSITY, "Creating Tests", 60, 2),
                        resultList.get(resultList.indexOf(list.get(1))));

                Assert.assertTrue(resultList.contains(list.get(1)));
                Assert.assertTrue(resultList.contains(list.get(2)));
                Assert.assertTrue(resultList.contains(list.get(3)));
                Assert.assertFalse(resultList.contains(list.get(0)));
        }


}
