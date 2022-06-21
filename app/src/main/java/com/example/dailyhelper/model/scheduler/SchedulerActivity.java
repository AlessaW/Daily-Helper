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

    private ListView listView;
    private AppDatabase appDatabase;
    private static final String TAG = "SchedulerActivity";
    private TextInputEditText duration;
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



//        ArrayList<Task> list = new ArrayList<>();
//        Task task = new Task("Remove Sticker", Sport, "Urgent", 25, 1);
//
//        list.add(task);
//        ArrayList<Task> arrayList = new ArrayList<>();
//
//        Task task_1 = new Task("Reomve Sticker", "clean up the desk", 1);
//        Task task_2 = new Task("Romve Sticker", "clean up the desk", 1);
//        Task task_3 = new Task("Romve Sticker", "clean up the desk", 1);




//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, arrayList);
//        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.list_item, arrayList);
//        listView.setAdapter(adapter);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);

    }

    @Override
    public void onItemClick(int position) {

    }
}