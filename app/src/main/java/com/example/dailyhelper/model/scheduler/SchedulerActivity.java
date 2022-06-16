package com.example.dailyhelper.model.scheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.dailyhelper.R;
import com.example.dailyhelper.controller.taskManager.RecyclerViewAdapter;
import com.example.dailyhelper.controller.taskManager.TaskListFragment;
import com.example.dailyhelper.taskManagerDataBase.AppDatabase;
import com.example.dailyhelper.taskManagerDataBase.Task;
import com.example.dailyhelper.taskManagerDataBase.TaskCategory;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;

public class SchedulerActivity extends AppCompatActivity {

    private ListView listView;
    private AppDatabase appDatabase;
    private static final String TAG = "SchedulerActivity";
    private TextInputEditText duration;
    private List<com.example.dailyhelper.taskManagerDataBase.Task> testList= new ArrayList<com.example.dailyhelper.taskManagerDataBase.Task>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "onCreate: Started");

        listView = findViewById(R.id.listview);
//        duration = findViewById(R.id.durationInput);
//
//        SimpleScheduler scheduler = new SimpleScheduler(appDatabase);
//
//        List<Task> arraylist = scheduler.scheduleTasks(Integer.parseInt(duration.toString()));
//        Task task = new Task("playing Football", TaskCategory.SPORT, "just a casual Match of Football", 30, 3);
//        arraylist.add(task);
//
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, arraylist);
//        listView.setAdapter(adapter);

//        ArrayList<Task> arrayList = (ArrayList<Task>) scheduler.scheduleTasks(Integer.parseInt(duration.toString()));;
//        List<Task> arrayList = scheduler.scheduleTasks(Integer.parseInt(duration.toString()));

        SimpleScheduler scheduler = new SimpleScheduler(appDatabase);

        ArrayList<com.example.dailyhelper.model.scheduler.Task> arrayList = new ArrayList<>();
        com.example.dailyhelper.model.scheduler.Task task_1 = new com.example.dailyhelper.model.scheduler.Task("Remove Sticker", "Clean up the desk", 1);
        com.example.dailyhelper.model.scheduler.Task task_2 = new com.example.dailyhelper.model.scheduler.Task("Remove Sticker", "Clean up the desk", 1);
        com.example.dailyhelper.model.scheduler.Task task_3 = new com.example.dailyhelper.model.scheduler.Task("Remove Sticker", "Clean up the desk", 1);

//        ArrayList<Task> list = new ArrayList<>();
//        Task task = new Task("Remove Sticker", Sport, "Urgent", 25, 1);
//
//        list.add(task);
//        ArrayList<Task> arrayList = new ArrayList<>();
//
//        Task task_1 = new Task("Reomve Sticker", "clean up the desk", 1);
//        Task task_2 = new Task("Romve Sticker", "clean up the desk", 1);
//        Task task_3 = new Task("Romve Sticker", "clean up the desk", 1);
//
        arrayList.add(task_1);
        arrayList.add(task_2);
        arrayList.add(task_3);

        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.scheduler_list_item, arrayList);
        listView.setAdapter(adapter);


//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, arrayList);
//        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.list_item, arrayList);
//        listView.setAdapter(adapter);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);

    }
}