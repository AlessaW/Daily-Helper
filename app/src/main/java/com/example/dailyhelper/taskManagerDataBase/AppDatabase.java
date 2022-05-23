package com.example.dailyhelper.taskManagerDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao TaskDao();

    private static AppDatabase instance;

    public static AppDatabase getDbInstance(Context context){
        if (instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"Database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
