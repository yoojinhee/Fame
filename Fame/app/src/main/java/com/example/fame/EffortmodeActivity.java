package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class EffortmodeActivity extends AppCompatActivity {

    Button addButton;
    Button slideButton;
    Button alarmButton;
    Cursor cursor;
    DBHelper dbHelper;
    WordAdapter adapter;
    ListView listView;
    String table;
    SQLiteDatabase db;
    String buttontextcolor;
    String buttonchangecolor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effortmode);

        listView=findViewById(R.id.word_note);
        View footer = getLayoutInflater().inflate(R.layout.activity_effortmode_footer, null, false) ;
        addButton = (Button) footer.findViewById(R.id.addButton);
        slideButton = (Button) findViewById(R.id.slideButton);
        alarmButton = (Button) findViewById(R.id.alarmButton);
        table="";
        buttontextcolor="#017BEC";
        buttonchangecolor="#FF666363";
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        dbHelper=DBHelper.getInstance(this);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
        listView.addFooterView(footer);
        if(recordalarmcount()>0){
            alarmlist();
        }else if(recordslidecount()>0){
            slidelist();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
        slideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidelist();
            }
        });
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmlist();
            }
        });
    }

    public void slidelist(){
        table="slide";
        slideButton.setTextColor(Color.parseColor(buttontextcolor));
        alarmButton.setTextColor(Color.parseColor(buttonchangecolor));

        cursor = dbHelper.getReadableDatabase().query(
                SlideCategory.CategoryEntry.TABLE_NAME,
                null,null,null,null,null,null);
        adapter=new WordAdapter(EffortmodeActivity.this,cursor);
        listView.setAdapter(adapter);
    }
    public void alarmlist(){
        table="alarm";
        alarmButton.setTextColor(Color.parseColor(buttontextcolor));
        slideButton.setTextColor(Color.parseColor(buttonchangecolor));
        cursor = dbHelper.getReadableDatabase().query(
                AlarmCategory.CategoryEntry.TABLE_NAME,
                null,null,null,null,null,null);
        adapter=new WordAdapter(EffortmodeActivity.this,cursor);
        listView.setAdapter(adapter);
    }
    public int recordslidecount() {
        String sql = "SELECT * FROM SlideCategory";
        return cnt(sql);
    }//끈기모드 데이터베이스 레코드 개수 반환

    public int recordalarmcount() {
        String sql = "SELECT * FROM AlarmCategory";
        return cnt(sql);
    }

    public int cnt(String sql){
        int cnt=0;
        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery(sql, null);
        cnt+=cursor.getCount();
        return cnt;
    }
    private class WordAdapter extends CursorAdapter{

        public WordAdapter(Context context, Cursor c) {
            super(context, c,false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1,parent,false);

        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView titleText=view.findViewById(android.R.id.text1);
            if(table.equals("slide"))
            titleText.setText(cursor.getString(cursor.getColumnIndexOrThrow(SlideCategory.CategoryEntry.COLUMN_NAME_LEVEL)));
            else if(table.equals("alarm"))
            titleText.setText(cursor.getString(cursor.getColumnIndexOrThrow(AlarmCategory.CategoryEntry.COLUMN_NAME_LEVEL)));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(EffortmodeActivity.this,SelModeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//이전 엑티비티 다 죽이기
                //setResult(Activity.RESULT_OK, intent);
                startActivity(intent);
                //finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }//뒤로가기
}
