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

public class SlideSetActivity extends AppCompatActivity {

    Button nextButton;
    Button missionButton;
    Button repeatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_set);

        nextButton=findViewById(R.id.nextButton);
        missionButton=findViewById(R.id.missionButton);
        repeatButton=findViewById(R.id.repeatButton);

        ((InputSetActivity)InputSetActivity.mContext).result="reset";
        ((SliderepeatSetActivity)SliderepeatSetActivity.mContext).result="reset";

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LevelSetActivity.class);
                startActivity(intent);
            }
        });
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
                Intent intent=new Intent(getApplicationContext(),SliderepeatSetActivity.class);
                startActivityForResult(intent,200);
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
            if(missionButton.getText().toString().equals("기본")) {
                ((InputSetActivity)InputSetActivity.mContext).result="reset";
            }
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + category + "," + count, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == 200) {//반복
            if (resultCode == Activity.RESULT_OK) {
                int count = data.getIntExtra("슬라이드반복",-1);
                repeatButton.setText(count+"분마다");
                if(count==0){
                    repeatButton.setText("항상");
                }
                Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + count, Toast.LENGTH_SHORT).show();
            }
        }
    }
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
