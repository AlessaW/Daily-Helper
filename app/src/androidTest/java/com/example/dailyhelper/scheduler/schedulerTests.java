package com.example.dailyhelper.scheduler;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.scheduler.IScheduler;
import com.example.dailyhelper.model.scheduler.SimpleScheduler;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(AndroidJUnit4.class)
public class schedulerTests {

        @Test
        public void testData(){
                Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

                AppDatabase appDatabase = AppDatabase.getInMemoryDbInstance(appContext);

                appDatabase.TaskDao().insertTask(
                        new Task("Playing Winx Game", TaskCategory.GAMING, "Having Fun playing", 180, 1),
                        new Task("Se3 Project", TaskCategory.UNIVERSITY, "Creating Tests", 60, 2),
                        new Task("Another Task", TaskCategory.FRIENDS, "", 30, 3),
                        new Task("Task 4", TaskCategory.GROCERY, "", 30, 4)
                );

                IScheduler scheduler = new SimpleScheduler(appDatabase);

                Task task = appDatabase.TaskDao().getAllTasks().get(0);
                Log.i("task", task.getName());

                List<Task> list = scheduler.scheduleTasks(120);

                String result = list.stream()
                        .map(i -> i.getName())
                        .collect(Collectors.joining(","));

                Log.i("test", result);

                Assert.assertNotNull(list.get(0));

        }









}
