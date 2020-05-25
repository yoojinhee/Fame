package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BasicWordActivity extends AppCompatActivity {

    TextView exText;
    TextView startText;
    TextView endText;
    TextView levelText;
    EditText inputText;

    ImageButton nextButton;
    ImageButton previousButton;
    Button listButton;
    Button CheckButton;
    Button wordButton;

    String level;
    int startnum,endnum;
    int page;
    Cursor cursor;
    int cardpage;
    String highlight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_word);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        Intent intent=getIntent();
        level=intent.getStringExtra("level");
        startnum=intent.getIntExtra("startnum",-1);
        endnum=intent.getIntExtra("endnum",-1);

        cardpage=0;//홀수일땐 의미 짝수일땐 영어단어

        nextButton = (ImageButton) findViewById(R.id.rightButton);
        previousButton = (ImageButton) findViewById(R.id.leftButton);
        listButton = (Button) findViewById(R.id.listButton);
        CheckButton = (Button) findViewById(R.id.CheckButton);
        wordButton = (Button) findViewById(R.id.wordButton);

        exText = findViewById(R.id.exText);
        startText = findViewById(R.id.startText);
        inputText=findViewById(R.id.inputText);
        endText = findViewById(R.id.endText);
        endText.setText(Integer.toString(endnum));
        levelText = findViewById(R.id.levelText);
        levelText.setText(intent.getStringExtra("levelText"));

        if(intent.getIntExtra("page",-1)!=-1)
            page=intent.getIntExtra("page",-1);
        else page=startnum;

        Log.e("",page+"");
        Log.e("",startnum+"");

        startText.setText(Integer.toString(page));
        SQLiteDatabase db = DBHelper.getInstance(BasicWordActivity.this).getReadableDatabase();
        String sql = "SELECT * FROM "+level;
        cursor = db.rawQuery(sql, null);
        cursor.moveToPosition(page-1);

        //wordButton.setText(cursor.getString(cursor.getColumnIndex("WORD")));
        changeWord();

        wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardpage++;
                if(cardpage%2==0){
                    wordButton.setText(cursor.getString(cursor.getColumnIndex("WORD")));
                }else{
                    wordButton.setText(cursor.getString(cursor.getColumnIndex("MEAN")));
                }
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BasicWordActivity.this,BasicWordListActivity.class);
                intent.putExtra("startnum",startnum);
                intent.putExtra("endnum",endnum);
                intent.putExtra("level",level);
                intent.putExtra("page",page);
                startActivity(intent);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardpage=0;
                if(page<endnum){
                    page++;
                    Log.e("page",page+"");
                    cursor.moveToNext();
                    changeWord();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardpage=0;
                if(page>startnum){
                    page--;
                    Log.e("page",page+"");
                    cursor.moveToPrevious();
                    changeWord();
                }
            }
        });
        CheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputText.getText().toString().equals(cursor.getString(cursor.getColumnIndex("WORD")))){
                    Toast.makeText(BasicWordActivity.this, "O", Toast.LENGTH_SHORT).show();
                    inputText.setText(null);
                }else{
                    Toast.makeText(BasicWordActivity.this, "X", Toast.LENGTH_SHORT).show();
                    inputText.setText(null);
                }
            }
        });
    }
    public void highlight(){
        String content = cursor.getString(cursor.getColumnIndex("EX"));
        SpannableString spannableString = new SpannableString(content);
        int start = content.indexOf(cursor.getString(cursor.getColumnIndex("WORD")));
        int end = start + cursor.getString(cursor.getColumnIndex("WORD")).length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#037CEC")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        exText.setText(spannableString);
    }

    public void changeWord(){
        wordButton.setText(cursor.getString(cursor.getColumnIndex("WORD")));
        highlight();
        startText.setText(Integer.toString(page));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(BasicWordActivity.this, BasicmodeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//이전 엑티비티 다 죽이기
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }//뒤로가기
}