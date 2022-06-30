package com.example.dailyhelper.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import java.util.List;
import java.util.Observable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    Flowable<List<Task>> getAllTasks();

    @Query("Update Task set name = :iName, category = :iCategory,description = :iDescription" +
            ", duration = :iDuration, priority = :iPriority Where id= :id")
    Completable update(int id, String iName, TaskCategory iCategory, String iDescription,
                int iDuration, int iPriority);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable  insertTask(Task... task);

    @Delete
    Completable delete(Task task);

}
