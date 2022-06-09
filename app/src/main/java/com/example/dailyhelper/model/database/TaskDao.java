package com.example.dailyhelper.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAllTasks();

    @Query("Update Task set name = :iName, category = :iCategory,description = :iDescription" +
            ", duration = :iDuration, priority = :iPriority Where id= :id")
    void update(int id, String iName, TaskCategory iCategory, String iDescription,
                int iDuration, int iPriority);

    @Insert
    void insertTask(Task... task);



    @Delete
    void delete(Task task);

}
