package com.example.dailyhelper.model.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dailyhelper.model.taskmanager.Task;

@Database(entities = {Task.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao TaskDao();

    private static AppDatabase instance;

    public static AppDatabase getDbInstance(Context context){
        if (instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"Database")
                    .allowMainThreadQueries()
                    .build();
            Log.i("AppDatabase" ,"Builds and initialize the Database and return an instance" );
        }

        return instance;
    }

}
