package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class LevelSetActivity extends AppCompatActivity {

    ImageButton nextbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_set);

        nextbutton=findViewById(R.id.nextButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        Intent intent=getIntent();

        String hour = "",minute="",category;
        int index[],wordcount=-1,repeatcount=-1,inputcount=-1;

        category = intent.getStringExtra("category");
        if(intent.getStringExtra("mode").equals("알람")) {
            hour = intent.getStringExtra("hour");
            minute = intent.getStringExtra("minute");
            index = intent.getIntArrayExtra("dayindex");
        }else if(intent.getStringExtra("mode").equals("슬라이드")) {
            wordcount = intent.getIntExtra("wordcount", -1);
            repeatcount = intent.getIntExtra("repeatcount", -1);
        }

        if(category.equals("입력하기")) {
            inputcount = intent.getIntExtra("inputcount", -1);
            Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + category + "," + wordcount+ "," + repeatcount+ "," + inputcount, Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(getApplicationContext(), "메뉴화면으로부터 응답 : " + category + "," + wordcount+ "," + repeatcount , Toast.LENGTH_LONG).show();

        Spinner levelSpinner=(Spinner)findViewById(R.id.levelSpinner);
        ArrayAdapter levelAdapter = ArrayAdapter.createFromResource(this, R.array.level, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);//arrays.xml과 Spinner 연결
//        levelSpinner.getAdapter();
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CompleteActivity.class);
                startActivity(intent);
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
