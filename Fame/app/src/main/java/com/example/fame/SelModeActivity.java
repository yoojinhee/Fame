package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class SelModeActivity extends AppCompatActivity {

    ImageButton effortButton;
    //    ImageButton basicButton;
    SQLiteDatabase db;
    DBHelper dbHelper;
    public List<Word> List ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_mode);

        initLoadDB();

        effortButton = (ImageButton) findViewById(R.id.effortButton);
//        basicButton=(ImageButton)findViewById(R.id.basicButton);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        dbHelper = DBHelper.getInstance(this);
        db = dbHelper.getReadableDatabase();
        Toast.makeText(SelModeActivity.this, ""+count(),Toast.LENGTH_SHORT).show();

        effortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count()<=0){
                    Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), EffortmodeActivity.class);
                    intent.putExtra("mode","null");
                    startActivity(intent);
                }
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

    public int count() {
        int cnt = 0;
        Cursor cursor;
        String slidesql = "SELECT * FROM SlideCategory";
        cursor = db.rawQuery(slidesql, null);
        cnt+=cursor.getCount();
        String alarmsql = "SELECT * FROM AlarmCategory";
        cursor = db.rawQuery(alarmsql, null);
        cnt+=cursor.getCount();
        return cnt;
    }//끈기모드 데이터베이스 레코드 개수 반환

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //뒤로가기

    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        // db에 있는 값들을 model을 적용해서 넣는다.
        List = mDbHelper.getTableData();
        // db 닫기
        mDbHelper.close();
    }

}
