package com.example.dailyhelper;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.icu.lang.UCharacter;
import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.example.dailyhelper.controller.taskManager.TaskManagerActivity;
import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
     public ActivityScenarioRule mActivityRule = new ActivityScenarioRule<>(
            TaskManagerActivity.class);

    AppDatabase db;
    List<Task> taskList = new ArrayList<Task>();
    Task lastTask;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.dailyhelper", appContext.getPackageName());
    }


    @Test
    public void addTaskTest() {

        updateDBTaskList();

        onView(withId(R.id.addTask)).perform(click());
        onView(withId(R.id.addTaskNameTextView)).check(matches(isDisplayed()));


        onView(withId(R.id.addTaskName)).perform(typeText("Watching a show"));
        onView(withId(R.id.addTaskCategory)).perform(typeText("UNIVERSITY"));
        onView(withId(R.id.addTaskDuration)).perform(typeText("12"));
        onView(withId(R.id.addTaskPriority)).perform(typeText("5"));
        onView(withId(R.id.addTaskPriority)).perform(pressImeActionButton());
        onView(withId(R.id.addTaskDescription)).perform(typeText("Im watching a show for the uni ;)"));
        onView(withId(R.id.addTaskDescription)).perform(pressImeActionButton());
        onView(withId(R.id.addTaskButton)).perform(click());

        Task task = new Task("Watching a show", TaskCategory.UNIVERSITY, "Im watching a show for the uni ;)", 12, 5);

        dbAssertLast(task);

    }


    @Test
    public void addTaskCancelTest(){

        setLastTask();

        onView(withId(R.id.addTask)).perform(click());
        onView(withId(R.id.addTaskNameTextView)).check(matches(isDisplayed()));


        onView(withId(R.id.addTaskName)).perform(pressImeActionButton());
        onView(withId(R.id.addTaskCategory)).perform(pressImeActionButton());
        onView(withId(R.id.addTaskDuration)).perform(pressImeActionButton());
        onView(withId(R.id.addTaskPriority)).perform(pressImeActionButton());
        onView(withId(R.id.addTaskDescription)).perform(pressImeActionButton());

        onView(withId(R.id.addTaskCancelButton)).perform(click());

        updateDBTaskList();
        dbAssertLast(lastTask);

    }



    public void dbAssertLast(Task task) {

        updateDBTaskList();

        Task lastTask = taskList.get(taskList.size() - 1);

        assertEquals(task.getName(), lastTask.getName());
        assertEquals(task.getCategory(), lastTask.getCategory());
        assertEquals(task.getDuration(), lastTask.getDuration());
        assertEquals(task.getPriority(), lastTask.getPriority());
        assertEquals(task.getDescription(), lastTask.getDescription());

    }

    public void updateDBTaskList(){

        db = AppDatabase.getDbInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());

        db.TaskDao().getAllTasks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws InterruptedException {

                        taskList = tasks;

                    }

                    public void onError(Throwable e) {

                    }
                });
        Log.i("The taskList has so many tasks: ", String.valueOf(taskList.size()));
    }



    public void setLastTask(){

        db = AppDatabase.getDbInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());

        db.TaskDao().getAllTasks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws InterruptedException {

                        taskList = tasks;

                        lastTask = taskList.get(taskList.size()-1);

                    }

                    public void onError(Throwable e) {

                    }
                });

        Log.i("The taskList has so many tasks: ",String.valueOf(taskList.size()));
    }
}