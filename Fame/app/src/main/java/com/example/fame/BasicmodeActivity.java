package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BasicmodeActivity extends AppCompatActivity {

    Button beggin01;
    Button beggin02;
    Button beggin03;
    Button beggin04;

    Button intermediate01;
    Button high01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicmode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기


        beggin01=(Button) findViewById(R.id.beggin01);
        beggin02=(Button) findViewById(R.id.beggin02);
        beggin03=(Button) findViewById(R.id.beggin03);
        beggin04=(Button) findViewById(R.id.beggin04);

        intermediate01=(Button) findViewById(R.id.intermediate01);
        high01=(Button) findViewById(R.id.high01);

        beggin01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasicWordActivity.class);
                intent.putExtra("startnum",1);
                intent.putExtra("endnum",50);
                intent.putExtra("level","BeginningLevel");
                intent.putExtra("levelText","초급");
                startActivity(intent);
            }

        });
        beggin02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasicWordActivity.class);
                intent.putExtra("startnum",51);
                intent.putExtra("endnum",100);
                intent.putExtra("level","BeginningLevel");
                intent.putExtra("levelText","초급");
                startActivity(intent);
            }
        });
        beggin03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasicWordActivity.class);
                intent.putExtra("startnum",101);
                intent.putExtra("endnum",150);
                intent.putExtra("level","BeginningLevel");
                intent.putExtra("levelText","초급");
                startActivity(intent);
            }
        });
        beggin04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasicWordActivity.class);
                intent.putExtra("startnum",151);
                intent.putExtra("endnum",200);
                intent.putExtra("level","BeginningLevel");
                intent.putExtra("levelText","초급");
                startActivity(intent);
            }
        });

        intermediate01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasicWordActivity.class);
                intent.putExtra("startnum",1);
                intent.putExtra("endnum",50);
                intent.putExtra("level","intermediateLevel");
                intent.putExtra("levelText","중급");
                startActivity(intent);
            }

        });
        high01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasicWordActivity.class);
                intent.putExtra("startnum",51);
                intent.putExtra("endnum",100);
                intent.putExtra("level","highLevel");
                intent.putExtra("levelText","고급");
                startActivity(intent);
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(BasicmodeActivity.this, SelModeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//이전 엑티비티 다 죽이기
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }//뒤로가기
}