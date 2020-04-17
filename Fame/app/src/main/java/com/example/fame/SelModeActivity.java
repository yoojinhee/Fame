package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelModeActivity extends AppCompatActivity {

    ImageButton effortButton;
//    ImageButton basicButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_mode);

        effortButton=(ImageButton)findViewById(R.id.effortButton);
//        basicButton=(ImageButton)findViewById(R.id.basicButton);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        effortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(intent);
            }
        });

//        basicButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent=new Intent(getApplicationContext(),.class);
////                startActivity(intent);
//                Intent intent=new Intent(getApplicationContext(),CategoryActivity.class);
//                startActivity(intent);
//            }
//        });
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
