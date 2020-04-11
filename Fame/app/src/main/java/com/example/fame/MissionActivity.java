package com.example.fame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MissionActivity extends AppCompatActivity {

    Button inputButton;
    Button BasicButton;
    static int result=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        inputButton=(Button) findViewById(R.id.inputButton);
        BasicButton=(Button) findViewById(R.id.BasicButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),InputSetActivity.class);
                startActivityForResult(intent,101);
            }
        });
        BasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("category", "기본");
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            Intent intent = new Intent();
            int count = intent.getIntExtra("count", -1);
            String category = data.getStringExtra("category");
            intent.putExtra("category", category);
            intent.putExtra("count", count);
            if(resultCode==0) {//input
                setResult(0, intent);
                result=0;
                finish();
            }else if(resultCode==1){//기본
                result=1;
            }else if(resultCode==2){
                result=2;
            }
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
                if(result==1) {
                    intent.putExtra("category", "기본");
                    setResult(1, intent);

                }else if(result==0||result==2){
                    intent.putExtra("category", "입력하기");
                    setResult(1, intent);
                }
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //뒤로가기
}

