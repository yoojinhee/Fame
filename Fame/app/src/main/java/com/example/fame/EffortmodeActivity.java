package com.example.fame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EffortmodeActivity extends AppCompatActivity {

    Button addButton;
    Button slideButton;
    Button alarmButton;

    Cursor cursor;
    DBHelper dbHelper;
    SQLiteDatabase db;

    ListViewAdapter listViewAdapter;
    ListView listView;

    String table;
    String table_name;
    String table_id;
    String buttontextcolor;
    String buttonchangecolor;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effortmode);

        mContext = this;
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
        table_name=SlideCategory.CategoryEntry.TABLE_NAME;
        table_id= SlideCategory.CategoryEntry.COLUMN_NAME_ID;
        slideButton.setTextColor(Color.parseColor(buttontextcolor));
        alarmButton.setTextColor(Color.parseColor(buttonchangecolor));
        list();
    }

    public void alarmlist(){
        table="alarm";
        table_name=AlarmCategory.CategoryEntry.TABLE_NAME;
        table_id= AlarmCategory.CategoryEntry.COLUMN_NAME_ID;
        alarmButton.setTextColor(Color.parseColor(buttontextcolor));
        slideButton.setTextColor(Color.parseColor(buttonchangecolor));
        list();
    }
    public void list(){
        cursor = getCursor();
        listViewAdapter=new ListViewAdapter(EffortmodeActivity.this,cursor,table);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//리스트 눌렀을때 단어장으로 이동
                Intent intent=new Intent(EffortmodeActivity.this,WordActivity.class);
                Cursor cursor=(Cursor) listViewAdapter.getItem(position);

                intent.putExtra("id",id);
                view.setFocusable(false);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//길게 눌렀을때 삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(EffortmodeActivity.this);
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db=DBHelper.getInstance(EffortmodeActivity.this).getWritableDatabase();
                        int deletedcount=db.delete(table_name,
                                    table_id+"="+id,null);
                            if(deletedcount==0){
                                Toast.makeText(EffortmodeActivity.this, "삭제하는데 문제 발생", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                listViewAdapter.swapCursor(getCursor());
                                if(table.equals("slide"))slidelist();
                                else if (table.equals("alarm"))alarmlist();
                                Toast.makeText(EffortmodeActivity.this, "삭제함", Toast.LENGTH_SHORT).show();
                            }
                    }
                });
                builder.setNegativeButton("취소",null);
                builder.show();
                return true;
            }
        });
    }

    private Cursor getCursor(){
        return dbHelper.getReadableDatabase().query(
                table_name,
                null,null,null,null,null,table_id+" DESC");
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
    public void alarmsetlayout(View view){
        TextView text=view.findViewById(R.id.text);
        TextView hourtext=view.findViewById(R.id.hourtext);
        TextView minutetext=view.findViewById(R.id.minutetext);

        int hour=Integer.parseInt(cursor.getString(1));
        if(hour>=12&&hour<24){
            text.setText("오후");
        }
        if(hour>=13&&hour<=24){
            hour-=12;
        }else if(hour==0){
            hour+=12;
        }
        hourtext.setText(Integer.toString(hour));
        minutetext.setText(cursor.getString(cursor.getColumnIndexOrThrow(AlarmCategory.CategoryEntry.COLUMN_NAME_MINUTE)));
    }
    public void slidesetlayout(View view){
        TextView wordcntText=view.findViewById(R.id.wordcntText);
        wordcntText.setText(cursor.getString(cursor.getColumnIndexOrThrow(SlideCategory.CategoryEntry.COLUMN_NAME_WORDCOUNT)));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(EffortmodeActivity.this,SelModeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//이전 엑티비티 다 죽이기
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }//뒤로가기
}