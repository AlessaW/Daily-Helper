package com.example.dailyhelper.taskmanager;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

@RunWith(AndroidJUnit4.class)
public class DbTests {

    private AppDatabase db;
    private List<Task> taskList = new ArrayList<Task>();
    private Task lastTask;
    private CountDownLatch lock = new CountDownLatch(1);


    @Test
    public void addTaskToDbTest() throws InterruptedException {

        Task newTask = new Task("Playing with friends", TaskCategory.FRIENDS, "Having some fun with my friends", 60, 2);
        db = AppDatabase.getDbInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.TaskDao().insertTask(newTask).subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("AddTaskFragment" , "added the Task to the Database with the Values from the EditText Elements");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });;

        updateDBTaskList();


        assertEquals(newTask.getName(), lastTask.getName());
        assertEquals(newTask.getCategory(), lastTask.getCategory());
        assertEquals(newTask.getDuration(), lastTask.getDuration());
        assertEquals(newTask.getPriority(), lastTask.getPriority());
        assertEquals(newTask.getDescription(), lastTask.getDescription());

    }

    @Test
    public void deleteTaskFromDbTest() throws InterruptedException {

        updateDBTaskList();

        Task oldTask = taskList.get(taskList.size()-1);
        oldTask = new Task(oldTask.getName(), oldTask.getCategory(), oldTask.getDescription(), oldTask.getDuration(), oldTask.getPriority());

        db = AppDatabase.getDbInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.TaskDao().delete(oldTask).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("Thread Item Delete"," Processing on Thread " +Thread.currentThread().getName());
                        Log.i("EditTaskFragment" ,"Delete the Selected Task from the Database using the id");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        updateDBTaskList();

        if (taskList.size() != 0){
            lastTask = taskList.get(taskList.size()-1);

            Log.i("The Tasks are: ", oldTask + "" + lastTask);

            assertNotEquals(oldTask, lastTask);
        }

    }


    @Test
    public void updateDbTest() throws InterruptedException {

        updateDBTaskList();

        if (taskList.size() == 0){
            Task newTask = new Task("Playing with friends", TaskCategory.FRIENDS, "Having some fun with my friends", 60, 2);
            db = AppDatabase.getDbInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
            db.TaskDao().insertTask(newTask).subscribeOn(Schedulers.computation())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            Log.i("AddTaskFragment" , "added the Task to the Database with the Values from the EditText Elements");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });;
        }

        Task oldTask = taskList.get(taskList.size()-1);
        oldTask = new Task(oldTask.getName(), oldTask.getCategory(), oldTask.getDescription(), oldTask.getDuration(), oldTask.getPriority());

        db = AppDatabase.getDbInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.TaskDao().update(taskList.get(taskList.size()-1).getId(), "Sitting with friends", TaskCategory.FRIENDS, "Yayeeet", 70,1).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("Thread Item Delete"," Processing on Thread " +Thread.currentThread().getName());
                        Log.i("EditTaskFragment" ,"Delete the Selected Task from the Database using the id");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        updateDBTaskList();

        lastTask = taskList.get(taskList.size()-1);

        Log.i("The Tasks are: ", oldTask + "" + lastTask);

        assertNotEquals(oldTask, lastTask);

    }


    public void updateDBTaskList() throws InterruptedException {

        Log.i("UpdateDBTaskList: ", "was started");

        db = AppDatabase.getDbInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());

        db.TaskDao().getAllTasks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws InterruptedException {

                        Log.i("UpdateDBTaskList: ", "got into the db subscribe");

                        taskList = tasks;
                        lastTask = taskList.get(taskList.size()-1);

                        lock.countDown();

                    }

                    public void onError(Throwable e) {

                    }
                });

        lock.await(2000, TimeUnit.MILLISECONDS);

        Log.i("The taskList has so many tasks: ", String.valueOf(taskList.size()));
    }

}
