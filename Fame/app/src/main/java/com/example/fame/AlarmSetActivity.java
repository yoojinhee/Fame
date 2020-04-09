package com.example.fame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class AlarmSetActivity extends AppCompatActivity {

    TimePicker TimePicker;
    String hour;
    String minute;

    Button missionButton;
    Button repeatButton;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);

        TimePicker = (TimePicker) findViewById(R.id.time_picker);
        missionButton=(Button) findViewById(R.id.missionButton);
        repeatButton=(Button) findViewById(R.id.repeatButton);
        nextButton=(Button) findViewById(R.id.nextButton);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = TimePicker.getHour() + "";
            minute = TimePicker.getMinute() + "";
        } else {
            hour = TimePicker.getCurrentHour() + "";
            minute = TimePicker.getCurrentMinute() + "";
        }

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
//                Intent intent=new Intent(getApplicationContext(),MissionActivity.class);
//                startActivityForResult(intent,100);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {//미션
            String category = data.getStringExtra("category");
            int count = data.getIntExtra("count", -1);

//            전페이지에서 보낸 값을 받아오는 메서드
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + category + "," + count, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == 200) {//반복
//            String category = data.getStringExtra("category");
//            int count = data.getIntExtra("count", -1);
//
////            전페이지에서 보낸 값을 받아오는 메서드
//            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + category + "," + count, Toast.LENGTH_SHORT).show();
        }

    }
//    응답을 전달 받음
//    A에서 B로 갔다가 다시 A로 넘어올 때 사용하는, 안드로이드에서 제공하는 기본 메소드
}
