package com.example.dailyhelper.taskManagerDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAllTasks();

    @Query("Update Task set name = :sName Where id= :id")
    void update(int id,String sName);

    @Insert
    void insertTask(Task... task);



    @Delete
    void delete(Task task);

}
