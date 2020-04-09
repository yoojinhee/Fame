package com.example.fame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MissionActivity extends AppCompatActivity {

    Button inputButton;
    Button XButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        inputButton=(Button) findViewById(R.id.inputButton);
        XButton=(Button) findViewById(R.id.XButton);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),InputSetActivity.class);
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Intent intent = new Intent();
            int count = data.getIntExtra("count", -1);
            String category=data.getStringExtra("category");
            intent.putExtra("count", count);
            intent.putExtra("category",category);
            setResult(Activity.RESULT_OK, intent);
            finish();
//            전페이지에서 보낸 값을 받아오는 메서드
        }
    }
//    응답을 전달 받음
//    A에서 B로 갔다가 다시 A로 넘어올 때 사용하는, 안드로이드에서 제공하는 기본 메소드
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

