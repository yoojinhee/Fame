package com.example.fame;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SlideCompleteActivity extends AppCompatActivity {

    int wordcount;
    int repeatcount;
    String category;
    int inputcount;
    String level;
    protected static final String TABLE_NAME = "SlideCategory";
    ContentValues values;
    Button finishButton;

    SQLiteDatabase db;
    long newRowId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        finishButton=(Button)findViewById(R.id.finishButton);

        Intent intent = getIntent();
        wordcount = intent.getIntExtra("wordcount", -1);
        repeatcount = intent.getIntExtra("repeatcount", -1);
        level=intent.getStringExtra("level");
        category=intent.getStringExtra("category");

       // if (category.equals("입력하기")) {
            inputcount = intent.getIntExtra("inputcount", -1);
            Toast.makeText(getApplicationContext(), "슬라이드 : " + category + "," + wordcount+ "," + repeatcount+ "," + inputcount+"," + level, Toast.LENGTH_LONG).show();

      //  }

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((InputSetActivity)InputSetActivity.mContext).clearMyPrefs();//설정할때의 값 reset
                values = new ContentValues();
                values.put(SlideCategory.CategoryEntry.COLUMN_NAME_WORDCOUNT, wordcount);
                values.put(SlideCategory.CategoryEntry.COLUMN_NAME_REPEATCOUNT, repeatcount);
                values.put(SlideCategory.CategoryEntry.COLUMN_NAME_LEVEL, level);
                values.put(SlideCategory.CategoryEntry.COLUMN_NAME_CATEGORY, category);
                values.put(SlideCategory.CategoryEntry.COLUMN_NAME_INPUTCOUNT, inputcount);
                getDB();
                Intent intent=new Intent(SlideCompleteActivity.this,EffortmodeActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//이전 엑티비티 다 죽이기
                startActivity(intent);
            }
        });
    }
    public void getDB(){
        db= DBHelper.getInstance(this).getWritableDatabase();
        newRowId=db.insert(SlideCategory.CategoryEntry.TABLE_NAME,
                null,
                values);
        if(newRowId==-1){
            Toast.makeText(getApplicationContext(),"저장에 문제가 발생하였습니다",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"저장되었습니다",Toast.LENGTH_SHORT).show();
        }
    }
}

//        Toast.makeText(getApplicationContext(), "슬라이드 : " + category + "," + wordcount+ "," + repeatcount+"," + level, Toast.LENGTH_LONG).show();

//        TextView text=findViewById(R.id.textView25);
//        WordDBHelper wordbHelper=WordDBHelper.getInstance(this);
//
//        String sql ="SELECT * FROM BeginningLevel";
//
//
//        db = wordbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql,null);
//
//        while(cursor.moveToNext()) {
//            text.setText(cursor.getString(2));
//    }