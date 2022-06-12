package com.example.dailyhelper.model.scheduler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.dailyhelper.R;

import java.util.ArrayList;

public class SchedulerActivity extends AppCompatActivity {

    ListView listView;
    private static final String TAG = "SchedulerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        Log.d(TAG, "onCreate: Started");
        listView = findViewById(R.id.listview);

        ArrayList<Task> arrayList = new ArrayList<>();

        Task task_1 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_2 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_3 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_4 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_5 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_6 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_7 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_8 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_9 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_10 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_11 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_12 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_13 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_14 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_15 = new Task("Remove sticker", "clean up the desk", 1);
        Task task_16 = new Task("Remove sticker", "clean up the desk", 1);

        arrayList.add(task_1);
        arrayList.add(task_2);
        arrayList.add(task_3);
        arrayList.add(task_4);
        arrayList.add(task_5);
        arrayList.add(task_6);
        arrayList.add(task_7);
        arrayList.add(task_8);
        arrayList.add(task_9);
        arrayList.add(task_10);
        arrayList.add(task_11);
        arrayList.add(task_12);
        arrayList.add(task_13);
        arrayList.add(task_14);
        arrayList.add(task_15);
        arrayList.add(task_16);

        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.list_item, arrayList);
        listView.setAdapter(adapter);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);

    }
}