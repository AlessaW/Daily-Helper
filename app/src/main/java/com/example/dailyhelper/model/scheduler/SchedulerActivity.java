package com.example.dailyhelper.model.scheduler;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyhelper.R;
import com.example.dailyhelper.controller.taskManager.RecyclerViewAdapter;
import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;

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

          IScheduler scheduler = SchedulerFactory.createScheduler(null, 0 ,0);
          appDatabase.TaskDao().getAllTasks().subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<List<Task>>() {
                      @Override
                      public void accept(List<Task> tasks) {

                          testList= scheduler.scheduleTasks(d.getInt("d"),tasks);
                          mAdapter = new RecyclerViewAdapter(testList,getApplicationContext(),SchedulerActivity.this);
                          recyclerView.setAdapter(mAdapter);
                          mAdapter.notifyDataSetChanged();
                      }

                      public void onError(Throwable e){

                      }
                  });
    }

    @Override
    public void onItemClick(int position) {
    }
}