package com.example.fame;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class WordActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton leftButton;
    ImageButton rightButton;
    WordListViewAdapter wordListViewAdapter;
    ListView listView;
    TextView startText;
    TextView endText;
    int page;
    String category;
    Long id;
    private ArrayList<Word> arrayList=new ArrayList<>();
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Intent intent=getIntent();
        id=intent.getLongExtra("id",-1);
        category=intent.getStringExtra("Category");
        fileName=intent.getStringExtra("fileName");

        wordinfo();
        leftButton=(ImageButton)findViewById(R.id.leftButton);
        rightButton=(ImageButton)findViewById(R.id.rightButton);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
        listView=findViewById(R.id.listview);
        startText=findViewById(R.id.startText);
        page=Integer.parseInt(startText.getText().toString());
        endText=findViewById(R.id.endText);

        wordListViewAdapter=new WordListViewAdapter(WordActivity.this,arrayList,1);
        listView.setAdapter(wordListViewAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기

        Log.e("아이디", String.valueOf(id));
        Log.e("Category", category);
        endText.setText(Integer.toString(wordcount()/5));
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.leftButton:
                if(page>1){
                    page--;
                    startText.setText(Integer.toString(page));
                    wordListViewAdapter=new WordListViewAdapter(WordActivity.this,arrayList,page);
                    listView.setAdapter(wordListViewAdapter);
                }
                break;
            case R.id.rightButton:
                if(page<wordcount()/5){
                    page++;
                    startText.setText(Integer.toString(page));
                    wordListViewAdapter=new WordListViewAdapter(WordActivity.this,arrayList,page);
                    listView.setAdapter(wordListViewAdapter);
                }
                break;
        }
    }

    public int wordcount(){//외우는 단어 개수
        SQLiteDatabase db=DBHelper.getInstance(WordActivity.this).getReadableDatabase();
        String sql="";
        if(category.equals("slide")){
            sql = "SELECT * FROM SlideCategory WHERE _id="+id;
        }else if(category.equals("alarm")){
            sql = "SELECT * FROM AlarmCategory WHERE _id="+id;
        }
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        return Integer.parseInt(cursor.getString(cursor.getColumnIndex("wordcount")));
    }
    public void wordinfo(){
        String dir = getFilesDir().getAbsolutePath();
        //File f= new File(dir,slidefileName);
        File file = new File(dir, fileName);
        if(file.exists()){
            try {
                String data = getStringFromFile(file.getPath());
                JSONObject jsonObject = new JSONObject(data);

                JSONArray wordArray = jsonObject.getJSONArray("note");
                for(int i=0; i<wordArray.length(); i++)
                {
                    JSONObject wordArrayJSONObject = wordArray.getJSONObject(i);

                    Word word = new Word();

                    word.setWord(wordArrayJSONObject.getString("word"));
                    word.setMean(wordArrayJSONObject.getString("mean"));
                    //Toast.makeText(this, word.getWord(), Toast.LENGTH_SHORT).show();
                    arrayList.add(word);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String getStringFromFile(String path) {
        String json = "";

        try {
            InputStream inputStream=openFileInput(fileName);
            int fileSize = inputStream.available();

            byte[] buffer = new byte[fileSize];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(WordActivity.this,SelModeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//이전 엑티비티 다 죽이기
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }//뒤로가기
}



