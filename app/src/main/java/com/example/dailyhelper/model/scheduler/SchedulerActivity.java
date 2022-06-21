package com.example.dailyhelper.model.scheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;

import com.example.dailyhelper.model.taskmanager.TaskCategory;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SchedulerActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemListener{

    private AppDatabase appDatabase;
    private static final String TAG = "SchedulerActivity";
    private List<Task> testList= new ArrayList<Task>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "onCreate: Started");

        appDatabase = AppDatabase.getDbInstance(getApplicationContext());

        recyclerView = findViewById(R.id.schedulerRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

          Bundle d = getIntent().getExtras();
          SimpleScheduler scheduler = new SimpleScheduler(appDatabase);
          appDatabase.TaskDao().getAllTasks().subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<List<Task>>() {
                                      @Override
                                      public void accept(List<Task> tasks) throws Throwable {
                                          testList= scheduler.scheduleTasks(d.getInt("d"),tasks);
                                          mAdapter = new RecyclerViewAdapter(testList,getApplicationContext(),SchedulerActivity.this);
                                          recyclerView.setAdapter(mAdapter);
                                          mAdapter.notifyDataSetChanged();
                                      }
                                  });
    }

    @Override
    public void onItemClick(int position) {
    }
}