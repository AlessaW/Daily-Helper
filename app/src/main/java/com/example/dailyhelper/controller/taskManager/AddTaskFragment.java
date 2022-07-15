package com.example.dailyhelper.controller.taskManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.dailyhelper.R;
import com.example.dailyhelper.model.database.AppDatabase;
import com.example.dailyhelper.model.taskmanager.Task;
import com.example.dailyhelper.model.taskmanager.TaskCategory;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String[] itemsForDuration={"10","20","30","60","90","120","180"};
    private String[] itemsForPriority={"1","2","3","4"};
    private String[] itemsForCategory;
    private String mParam1;
    private String mParam2;
    private EditText addTaskName;
    private AutoCompleteTextView addTaskCategory;
    private AutoCompleteTextView addTaskDuration;
    private AutoCompleteTextView addTaskPriority;
    private ArrayAdapter<String> adapterItemsForDuration;
    private ArrayAdapter<String> adapterItemsForPriority;
    private ArrayAdapter<String> adapterItemsForCategory;
    private int duration;
    private int Priority;
    private String category;

    private EditText addTaskDescription;
    private Button addItemButton;
    private Button addTaskCancelButton;

    AppDatabase db;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTaskFragment newInstance(String param1, String param2) {
        AddTaskFragment fragment = new AddTaskFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        addTaskName = view.findViewById(R.id.addTaskName);
        addTaskCategory = view.findViewById(R.id.autoCompleteCategory);

        addTaskDuration = view.findViewById(R.id.autoCompleteDuration);
        addTaskPriority = view.findViewById(R.id.autoCompletePriority);

        itemsForCategory= TaskCategory.getNames(TaskCategory.class);

        adapterItemsForCategory = new ArrayAdapter<>(getContext(), R.layout.drop_down_item, itemsForCategory);

        adapterItemsForPriority = new ArrayAdapter<>(getContext(), R.layout.drop_down_item,itemsForPriority);

        adapterItemsForDuration = new ArrayAdapter<String>(getContext(), R.layout.drop_down_item, itemsForDuration);

        addTaskDuration.setAdapter(adapterItemsForDuration);
        addTaskPriority.setAdapter(adapterItemsForPriority);
        addTaskCategory.setAdapter(adapterItemsForCategory);

        addTaskCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                category= item;
            }
        });

        addTaskDuration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                duration= Integer.parseInt(item);
            }
        });

        addTaskPriority.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Priority= Integer.parseInt(item);
            }
        });

        addTaskDescription = view.findViewById(R.id.addTaskDescription);
        addItemButton = view.findViewById(R.id.addTaskButton);
        addTaskCancelButton = view.findViewById(R.id.addTaskCancelButton);

        db = AppDatabase.getDbInstance(view.getContext());

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.TaskDao().insertTask(new Task(
                        addTaskName.getText().toString(),
                                TaskCategory.valueOf(category),
                        addTaskDescription.getText().toString(),
                        duration,
                                Priority
                )).subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Log.i("AddTaskFragment" , "added the Task to the Database with the Values from the EditText Elements");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });



                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new TaskListFragment());
                fragmentTransaction.commit();
            }
        });

        addTaskCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new TaskListFragment());
                fragmentTransaction.commit();
                Log.i("AddTaskFragment" , "goes back to the TaskListFragment without adding the Task");
            }
        });

        return view;
    }
}