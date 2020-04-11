package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class TestActivity extends AppCompatActivity {

    TextView mLifecycleDisplay;
    String SAVE_INSTANCE_KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mLifecycleDisplay = (TextView) findViewById(R.id.tv_lifecycle_events_display);

        if (savedInstanceState != null
                && savedInstanceState.containsKey(SAVE_INSTANCE_KEY)) {
            String savedString = savedInstanceState.getString(SAVE_INSTANCE_KEY);
            mLifecycleDisplay.setText(savedString);
        }


    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putString(SAVE_INSTANCE_KEY,
                "onSaveInstanceState is called!\n");
    }


}
