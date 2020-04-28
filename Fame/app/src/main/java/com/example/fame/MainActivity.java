package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db1;
    DBHelper dbHelper2;
    public List<Word> userList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView text=findViewById(R.id.textView25);
        initLoadDB();//최초에 데이터베이스 생성
        //String sql ="SELECT * FROM SlideBasicCategory";

        //db1 = wordbHelper.getReadableDatabase();
       // Cursor cursor = db.rawQuery(sql,null);

        //while(cursor.moveToNext()) {
           // text.setText(cursor.getString(2));
       // }

//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            startForegroundService(new Intent(this,SlideService.class));
//        }else
//        startService(new Intent(this,SlideService.class));
    }
    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        // db에 있는 값들을 model을 적용해서 넣는다.
        userList = mDbHelper.getTableData();
        // db 닫기
        mDbHelper.close();
    }
}
