package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class ScreenOffActivity extends AppCompatActivity {

private ScreenOffReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_off);

        if(mReceiver==null){
            mReceiver=new ScreenOffReceiver();
            IntentFilter filter=new IntentFilter(Intent.ACTION_SCREEN_OFF);
            registerReceiver(mReceiver,filter);
        }
    }
}
