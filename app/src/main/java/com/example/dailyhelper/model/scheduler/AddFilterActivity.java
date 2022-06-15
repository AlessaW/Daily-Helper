package com.example.dailyhelper.model.scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailyhelper.R;

public class AddFilterActivity extends AppCompatActivity {

    private static final String TAG = "AddFilterActivity";
    private Button resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_duration);
        Log.d(TAG, "onCreate: Started");

        resultButton = findViewById(R.id.resultButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult();
            }
        });
    }

    public void showResult() {
        Intent intent = new Intent(this, SchedulerActivity.class);
        startActivity(intent);
    }

}
