package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Person;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmrepeatSetActivity extends AppCompatActivity {

    Button finishButton;
    CheckBox[] day;
    final int[] index=new int[7];

    private CompoundButton.OnCheckedChangeListener basic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmrepeat_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        finishButton = findViewById(R.id.finishButton);
        day = new CheckBox[]
                {
                        (CheckBox)findViewById(R.id.SunCheck),
                        (CheckBox)findViewById(R.id.monCheck),
                        (CheckBox)findViewById(R.id.TuesCheck),
                        (CheckBox)findViewById(R.id.WedsCheck),
                        (CheckBox)findViewById(R.id.ThursCheck),
                        (CheckBox)findViewById(R.id.FriCheck),
                        (CheckBox)findViewById(R.id.SaturCheck),
                };




        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayChecked(day,index);
                Intent intent = new Intent();
                intent.putExtra("요일",index);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //뒤로가기

    public void dayChecked(CheckBox[] day, final int[] index) {

        for(int i=0;i<day.length;i++){
            if(day[i].isChecked()==true){
                index[i]=1;
            }else{
                index[i]=0;
            }
        }
    }


}
