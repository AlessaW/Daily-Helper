package com.example.dailyhelper.model.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dailyhelper.R;


import java.util.ArrayList;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResource;

    private static class ViewHolder {
        TextView title;
        TextView des;
        TextView prio;
    }

    public TaskListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Task> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String des = getItem(position).getDescription();
        int prio = getItem(position).getPriority();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ViewHolder holder = new ViewHolder();

        holder.title = convertView.findViewById(R.id.task_title);
        holder.des = convertView.findViewById(R.id.task_description);
        holder.prio = convertView.findViewById(R.id.task_priority);

        convertView.setTag(holder);

        holder.title.setText(title);
        holder.des.setText(des);
        holder.prio.setText(Integer.toString(prio));

        return convertView;
    }
}
