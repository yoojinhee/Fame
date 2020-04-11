package com.example.fame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class LevelSetActivity extends AppCompatActivity {

    Button nextbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_set);

        nextbutton=findViewById(R.id.nextButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

//        Intent intent=getIntent();
//        String hour=intent.getStringExtra("시간");
//        String minute=intent.getStringExtra("분");
//        Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + hour + "," + minute, Toast.LENGTH_SHORT).show();
        Spinner levelSpinner=(Spinner)findViewById(R.id.levelSpinner);
        ArrayAdapter levelAdapter = ArrayAdapter.createFromResource(this, R.array.level, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);//arrays.xml과 Spinner 연결

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

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
