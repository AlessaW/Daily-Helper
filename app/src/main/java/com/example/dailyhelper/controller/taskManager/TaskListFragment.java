package com.example.dailyhelper.controller.taskManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailyhelper.R;
import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;
import com.example.dailyhelper.model.taskmanager.TaskSingletonPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment implements RecyclerViewAdapter.OnItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    AppDatabase db;
    List<Task> testList= new ArrayList<Task>();

    public TaskListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskListFragment newInstance(String param1, String param2) {
        TaskListFragment fragment = new TaskListFragment();
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
        View view= inflater.inflate(R.layout.fragment_task_list, container, false);


        fillTestList();
        recyclerView = view.findViewById(R.id.ListRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        db = AppDatabase.getDbInstance(view.getContext());

        testList = db.TaskDao().getAllTasks();

        mAdapter = new RecyclerViewAdapter(testList,getContext(),this);
        recyclerView.setAdapter(mAdapter);

        return view;


    }
    public void fillTestList(){
        db =  AppDatabase.getDbInstance(getContext());
        if (db.TaskDao().getAllTasks().isEmpty() ) {
            db.TaskDao().insertTask(new Task("playing Football", TaskCategory.SPORT, "just a casual Match of Football", 30, 3));
            db.TaskDao().insertTask(new Task("Reading A book", TaskCategory.SPORT, "read any book for at least 1 hour ", 1, 2));

        }

    }

    @Override
    public void onItemClick(int position) {
        String name = testList.get(position).getName();
        TaskSingletonPattern.getInstance().setId(testList.get(position).getId());
        TaskSingletonPattern.getInstance().setName(name);
        Log.i("what",TaskSingletonPattern.getInstance().getName());

        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, new EditTaskFragment());
        fragmentTransaction.commit();
    }

}