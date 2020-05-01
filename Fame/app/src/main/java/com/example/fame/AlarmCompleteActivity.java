package com.example.fame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AlarmCompleteActivity extends AppCompatActivity {

    String hour;
    String minute;
    int[] index;
    String category;
    int inputcount;
    String level;
    protected static final String TABLE_NAME = "AlarmCategory";
    ContentValues values;
    Button finishButton;

    SQLiteDatabase db;
    long newRowId;
    public static Context mContext;

    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        finishButton=(Button)findViewById(R.id.finishButton);

        final SlideCategory basiccategory=new SlideCategory();

        Intent intent = getIntent();
        hour = intent.getStringExtra("hour");
        minute = intent.getStringExtra("minute");
        index = intent.getIntArrayExtra("dayindex");
        category = intent.getStringExtra("category");
        level=intent.getStringExtra("level");
        inputcount = intent.getIntExtra("inputcount", -1);
//        Toast.makeText(getApplicationContext(), "슬라이드 : " + category + "," + hour+ "," + minute+ "," + inputcount+"," + level+","+index[0], Toast.LENGTH_LONG).show();

        this.calendar = Calendar.getInstance();

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {
                values = new ContentValues();
//                ((AlarmrepeatSetActivity)AlarmrepeatSetActivity.mContext).clearMyPrefs();
                values.put(AlarmCategory.CategoryEntry.COLUMN_NAME_HOUR, hour);
                values.put(AlarmCategory.CategoryEntry.COLUMN_NAME_MINUTE, minute);
                values.put(AlarmCategory.CategoryEntry.COLUMN_NAME_LEVEL, level);
                values.put(AlarmCategory.CategoryEntry.COLUMN_NAME_CATEGORY, category);
                values.put(AlarmCategory.CategoryEntry.COLUMN_NAME_INPUTCOUNT, inputcount);
                getDB();
                setAlarm();
                Intent intent=new Intent(AlarmCompleteActivity.this , EffortmodeActivity.class);
                startActivity(intent);
            }
        });
    }
    public void getDB(){
        db= DBHelper.getInstance(this).getWritableDatabase();
        newRowId=db.insert(AlarmCategory.CategoryEntry.TABLE_NAME,
                null,
                values);
        if(newRowId==-1){
            Toast.makeText(getApplicationContext(),"저장에 문제가 발생하였습니다",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"저장되었습니다",Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api= Build.VERSION_CODES.M)
    private void setAlarm(){//알람 설정

        Cursor cursor;
        DBHelper dbHelper;
        dbHelper=DBHelper.getInstance(this);
        cursor = dbHelper.getReadableDatabase().query(
                AlarmCategory.CategoryEntry.TABLE_NAME,
                null,null,null,null,null,null);

        cursor.moveToLast();
        String hour=cursor.getString(1);
        String minute=cursor.getString(2);

        Log.e("",hour);
        Log.e("",minute);

        this.calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour));
        this.calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        this.calendar.set(Calendar.SECOND, 0);


        Intent intent=new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //알람 설정
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);

        //Toast 보여주기(알람 시간 표시)
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault());
        Toast.makeText(this, "Alarm: " + format.format(calendar.getTime()), Toast.LENGTH_LONG).show();
    }
}
