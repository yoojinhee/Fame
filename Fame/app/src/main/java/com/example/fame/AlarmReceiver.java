package com.example.fame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent sIntent=new Intent(context,AlarmService.class);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            context.startForegroundService(sIntent);
        }
        else{
            context.startService(sIntent);
        }
    }
}
