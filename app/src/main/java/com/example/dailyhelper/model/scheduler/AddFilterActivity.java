package com.example.dailyhelper.model.scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dailyhelper.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddFilterActivity extends AppCompatActivity {

    private static final String TAG = "AddFilterActivity";
    private Button resultButton;
    private TextInputEditText duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_duration);
        Log.d(TAG, "onCreate: Started");

        resultButton  = findViewById(R.id.resultButton);
        duration = findViewById(R.id.durationInput);

        resultButton.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.toString().trim().length() == 0) {
                    resultButton.setEnabled(false);
                } else {
                    resultButton.setEnabled(true);
                    Log.d(TAG, "Input is provided");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        duration.addTextChangedListener(watcher);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Show task list");
                showResult();
            }
        });
    }

    public void showResult() {
        Intent intent = new Intent(this, SchedulerActivity.class);
        intent.putExtra("d",Integer.parseInt(duration.getText().toString()));
        startActivity(intent);
    }
}
