package com.example.fame;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AlarmSetActivity extends AppCompatActivity {

    TimePicker TimePicker;
    String hour;
    String minute;
    Button missionButton;
    Button repeatButton;
    Button nextButton;
    Button button;
    SeekBar seekBar;
    boolean Alarmrepeatresult;//알람 반복 설정을 하였는지

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);


        TimePicker = (TimePicker) findViewById(R.id.time_picker);
        missionButton = (Button) findViewById(R.id.missionButton);
        repeatButton = (Button) findViewById(R.id.repeatButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.getProgressDrawable().setColorFilter(Color.rgb(1, 123, 236), PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.rgb(1, 123, 236), PorterDuff.Mode.SRC_IN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        ((InputSetActivity) InputSetActivity.mContext).result = "기본";

        this.calendar = Calendar.getInstance();
        //현재 날짜 표시
        displayDate();

        this.TimePicker = findViewById(R.id.time_picker);

        findViewById(R.id.btnCalendar).setOnClickListener(mClickListener);
        findViewById(R.id.nextButton).setOnClickListener(mClickListener);


        missionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MissionActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlarmrepeatSetActivity.class);
                startActivityForResult(intent, 200);
            }
        });
        /*
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              TimeChanged();

                Intent intent = new Intent(getApplicationContext(), LevelSetActivity.class);
//                intent.putExtra("시간",hour);
//                intent.putExtra("분",minute);
                //((InputSetActivity)InputSetActivity.mContext).clearMyPrefs();

                if (Alarmrepeatresult == false) {
                    Toast.makeText(AlarmSetActivity.this, "반복 설정을 해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    startActivityForResult(intent, 300);
                }

            }
        });
         */

    }

    private AudioManager audioManager;

    public void onCreate()
    {
        SeekBar seek_volume = (SeekBar) findViewById(R.id.seekBar);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int nMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        int nCurrentVolumn = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        seek_volume.setMax(nMax);
        seek_volume.setProgress(nCurrentVolumn);
        seek_volume.setOnSeekBarChangeListener(seekChangeListener);
    }

    private SeekBar.OnSeekBarChangeListener seekChangeListener = new SeekBar.OnSeekBarChangeListener()
    {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            audioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, 0);
        }
    };



    private void displayDate() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
    }

    private void showDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DATE, dayOfMonth);

                //날짜 표시
                displayDate();
            }
        }, this.calendar.get(Calendar.YEAR), this.calendar.get(Calendar.MONTH), this.calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setAlarm() {
        //알람 시간 설정
        this.calendar.set(Calendar.HOUR_OF_DAY, this.TimePicker.getHour());
        this.calendar.set(Calendar.MINUTE, this.TimePicker.getMinute());
        this.calendar.set(Calendar.SECOND, 0);

        //receiver 설정

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //알람 설정
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);

        //Toast 보여주기(알람 시간 표시)
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault());
        Toast.makeText(this, "Alarm: " + format.format(calendar.getTime()), Toast.LENGTH_LONG).show();


    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCalendar:
                    //달력
                    showDatePicker();
                    break;

                case R.id.nextButton:
                    //알람등록
                    setAlarm();
                    break;
            }
        }
    };




    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {//미션
            String category = data.getStringExtra("category");
            int count = data.getIntExtra("count", -1);
            missionButton.setText(category);
//            전페이지에서 보낸 값을 받아오는 메서드
            if(missionButton.getText().toString().equals("기본")) {
                ((InputSetActivity)InputSetActivity.mContext).result="기본";
            }
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + category + "," + count, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == 200) {//반복
            if(resultCode== Activity.RESULT_OK) {
                int cnt=0;
                int[] index = data.getIntArrayExtra("요일");
                String day[] = {"일", "월", "화", "수", "목", "금", "토"};
                Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + index[0]+""+index[1]+""+index[2]+""+index[3]+""+index[4]+""+index[5]+""+index[6], Toast.LENGTH_SHORT).show();
                DayButtonSet(index, day);
            }
            if(repeatButton.getText().toString()=="안함"){
                Alarmrepeatresult=false;
            }else{
                Alarmrepeatresult=true;
            }

//            전페이지에서 보낸 값을 받아오는 메서드
        }
    }

    public void TimeChanged(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = TimePicker.getHour() + "";
            minute = TimePicker.getMinute() + "";
        } else {
            hour = TimePicker.getCurrentHour() + "";
            minute = TimePicker.getCurrentMinute() + "";
        }
    }//설정한 시간을 값에 넣어줌


    public void DayButtonSet(int[] index,String day[]){
        String text="";
        int count=0;

        for (int i = 0; i < index.length; i++) {
            if (index[i] == 1) {
                count++;
                text += day[i]+" ";
            }
        }
        if(index[0]==1&&index[6]==1&&count==2){
            text="주말";
        }
        if(index[1]==1&&index[2]==1&&index[3]==1&&index[4]==1&&index[5]==1&&count==5) {
            text = "평일";
        }
        if (count == 7) {
            text = "매일";
        }else if(count==0){
            text="안함";
        }
        repeatButton.setText(text);
    }//repeatset에서 가져온 값을 버튼 텍스트에 넣어줌

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Intent intent=new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //뒤로가기
}
