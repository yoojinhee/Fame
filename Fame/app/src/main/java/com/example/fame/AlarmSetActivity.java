package com.example.fame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;

public class AlarmSetActivity extends AppCompatActivity{

    TimePicker TimePicker;
    String hour;
    String minute;
    Button missionButton;
    Button repeatButton;
    Button nextButton;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);

        TimePicker = (TimePicker) findViewById(R.id.time_picker);
        missionButton=(Button) findViewById(R.id.missionButton);
        repeatButton=(Button) findViewById(R.id.repeatButton);
        nextButton=(Button) findViewById(R.id.nextButton);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        seekBar.getProgressDrawable().setColorFilter(Color.rgb(1,123,236), PorterDuff.Mode.SRC_IN );
        seekBar.getThumb().setColorFilter( Color.rgb(1,123,236), PorterDuff.Mode.SRC_IN );

        missionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MissionActivity.class);
                startActivityForResult(intent,100);
            }
        });

        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AlarmrepeatSetActivity.class);
                startActivityForResult(intent,200);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TimeChanged();

                Intent intent=new Intent(getApplicationContext(),LevelSetActivity.class);
//                intent.putExtra("시간",hour);
//                intent.putExtra("분",minute);
                ((InputSetActivity)InputSetActivity.mContext).clearMyPrefs();
                startActivityForResult(intent,300);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {//미션
            String category = data.getStringExtra("category");
            int count = data.getIntExtra("count", -1);
            missionButton.setText(category);
//            전페이지에서 보낸 값을 받아오는 메서드
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + category + "," + count, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == 200) {//반복
            if(resultCode== Activity.RESULT_OK) {
                int[] index = data.getIntArrayExtra("요일");
                String day[] = {"일", "월", "화", "수", "목", "금", "토"};
                DayButtonSet(index, day);
            }

//            전페이지에서 보낸 값을 받아오는 메서드
          //  Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + index[0]+""+index[1]+""+index[2]+""+index[3]+""+index[4]+""+index[5]+""+index[6], Toast.LENGTH_SHORT).show();
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
        }
        repeatButton.setText(text);
    }
}
