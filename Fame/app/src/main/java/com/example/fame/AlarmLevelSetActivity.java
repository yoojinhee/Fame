package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AlarmLevelSetActivity extends AppCompatActivity {

    ImageButton nextbutton;
    Spinner levelSpinner;
    ArrayAdapter levelAdapter;
    String hour;
    String minute;
    int[] index;
    String category;
    int inputcount;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_set);

        nextbutton=findViewById(R.id.nextButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        Intent intent=getIntent();

        hour = intent.getStringExtra("hour");
        minute = intent.getStringExtra("minute");
        index = intent.getIntArrayExtra("dayindex");
        category = intent.getStringExtra("category");
        inputcount=-1;
        //if(category.equals("입력하기")) {
            inputcount = intent.getIntExtra("inputcount", -1);
            Toast.makeText(getApplicationContext(), "알람 : " + hour + "," + minute+ "," + index[0]+ "," + inputcount +","+ category, Toast.LENGTH_LONG).show();
        //}
       // else Toast.makeText(getApplicationContext(), "알람 : " + hour + "," + minute+ "," + index[0] +","+ category, Toast.LENGTH_LONG).show();

        levelSpinner=(Spinner)findViewById(R.id.levelSpinner);
        levelAdapter = ArrayAdapter.createFromResource(this, R.array.level, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);//arrays.xml과 Spinner 연결
        levelSpinner.getAdapter();

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level=levelSpinner.getSelectedItem().toString();
                Intent intent=new Intent(getApplicationContext(),AlarmCompleteActivity.class);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                intent.putExtra("category",category);
                intent.putExtra("level", level);
                intent.putExtra("inputcount", inputcount);
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
