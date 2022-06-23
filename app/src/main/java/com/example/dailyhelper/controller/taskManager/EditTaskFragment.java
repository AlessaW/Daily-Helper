package com.example.dailyhelper.controller.taskManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dailyhelper.R;
import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText editTaskName;
    private EditText editTaskCategory;
    private EditText editTaskDuration;
    private EditText editTaskPriority;
    private EditText editTaskDescription;
    private Button editItemButton;
    private Button editTaskCancelButton;
    private Button editDeleteTaskButton;
    int itemId;

    List<Task> testList= new ArrayList<Task>();
    AppDatabase db;
    Task task = new Task();
    public EditTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditTaskFragment newInstance(String param1, String param2) {
        EditTaskFragment fragment = new EditTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_task, container, false);


        Bundle bundle = this.getArguments();
        itemId=bundle.getInt("Id");

        db = AppDatabase.getDbInstance(view.getContext());

        db.TaskDao().getAllTasks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<List<Task>>() {
                     @Override
                     public void accept(List<Task> tasks) throws Throwable {
                         testList= tasks;
                         Log.i("Thread Edit list"," Processing on Thread " +Thread.currentThread().getName());
                         Log.i("is list Empty ?","Size is " + testList.size());
                         if(itemId>=0){

                             for (Task t: testList){
                                 if (t.getId()==itemId) {
                                     task = t;

                                 }
                             }
                         }

                         Log.i("yes", task.getName());

                         editTaskName.setText(task.getName());
                         editTaskCategory.setText(String.valueOf(task.getCategory()));
                         editTaskPriority.setText(String.valueOf(task.getPriority()));
                         editTaskDuration.setText(String.valueOf(task.getDuration()));
                         editTaskDescription.setText(task.getDescription());
                     }

                     public void onError( Throwable e) {

                     }
                 });

        editDeleteTaskButton= view.findViewById(R.id.deleteTaskButton);
        editTaskName = view.findViewById(R.id.editTaskName);
        editTaskCategory=view.findViewById(R.id.editTaskCategory);
        editTaskDuration=view.findViewById(R.id.editTaskDuration);
        editTaskPriority=view.findViewById(R.id.editTaskPriority);
        editTaskDescription=view.findViewById(R.id.editTaskDescription);
        editItemButton=view.findViewById(R.id.editTaskButton);
        editTaskCancelButton=view.findViewById(R.id.editTaskCancelButton);


        editDeleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.TaskDao().delete(task).subscribeOn(Schedulers.io())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Log.i("Thread Item Delete"," Processing on Thread " +Thread.currentThread().getName());
                                Log.i("EditTaskFragment" ,"Delete the Selected Task from the Database using the id");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });

                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new TaskListFragment());
                fragmentTransaction.commit();
            }
        });

       editItemButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               db.TaskDao().update(itemId,editTaskName.getText().toString(),
                       TaskCategory.valueOf(editTaskCategory.getText().toString()),
                       editTaskDescription.getText().toString(),
                       Integer.parseInt(editTaskDuration.getText().toString()),
                       Integer.parseInt(editTaskPriority.getText().toString()))
                       .subscribeOn(Schedulers.io())
                       .subscribe(new CompletableObserver() {
                           @Override
                           public void onSubscribe(@NonNull Disposable d) {

                           }

                           @Override
                           public void onComplete() {
                               Log.i("Thread Edit Item"," Processing on Thread " +Thread.currentThread().getName());
                               Log.i("EditTaskFragment" ,"Change the Values of the variables of the Selected Task using the Values" +
                                       " from the Edit Task Elements using the id");
                           }

                           @Override
                           public void onError(@NonNull Throwable e) {

                           }
                       });

               FragmentTransaction fragmentTransaction = getActivity()
                       .getSupportFragmentManager().beginTransaction();
               fragmentTransaction.replace(R.id.fragmentContainerView, new TaskListFragment());
               fragmentTransaction.commit();
           }
       });

        editTaskCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new TaskListFragment());
                fragmentTransaction.commit();
                Log.i("EditTaskFragment" ,"goes back to the TaskListFragment without Editing the Task");
            }
        });


        return view;
    }
}