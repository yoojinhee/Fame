package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.speech.tts.TextToSpeech.ERROR;

public class QuizmodeActivity extends AppCompatActivity {

    TextView meanText;
    TextView engText;
    EditText answerText;
    TextView startText;
    TextView endText;
    Button chkButton;

    Cursor cursor;
    int cnt;
    int answercnt=0;
    int iarr[];
    final private int quizcount=21;//퀴즈수 + 1
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizmode);

        meanText=findViewById(R.id.meanText);
        engText=findViewById(R.id.engText);
        answerText=findViewById(R.id.answerText);
        startText=findViewById(R.id.startText);
        startText.setText("1");
        endText=findViewById(R.id.endText);
        endText.setText(Integer.toString(quizcount-1));
        chkButton=findViewById(R.id.chkButton);
        cnt=0;
        SQLiteDatabase db = DBHelper.getInstance(QuizmodeActivity.this).getReadableDatabase();
        String sql = "SELECT * FROM Quiz";
        cursor = db.rawQuery(sql, null);
        setQuizrandom();
        setQuizText(cnt);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.ENGLISH);
                    Log.e("wordtts", tts +"");

                }
            }
        });

        chkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerText.setText(null);
                if(isResult()){
                    Toast.makeText(QuizmodeActivity.this, "O", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(QuizmodeActivity.this, "X", Toast.LENGTH_SHORT).show();
                }
               // gettts();
                cnt++;
                if(cnt==quizcount-1){
                    Intent intent=new Intent(QuizmodeActivity.this,QuizResultActivity.class);
                    intent.putExtra("result",answercnt);
                    startActivity(intent);
                    finish();
                }else{
                    setQuizText(cnt);
                    startText.setText(Integer.toString(cnt+1));
                }
            }
        });
    }
    public void gettts(){
        tts.setPitch(0.5f);
        tts.speak(cursor.getString(cursor.getColumnIndex("quiz")),TextToSpeech.QUEUE_FLUSH, null);
    }
    //랜덤으로 퀴즈 배치
    public void setQuizrandom(){
        Random random=new Random();
        iarr=new int[quizcount];//중복 제거
        for(int i=0;i<quizcount;i++){
            iarr[i]=random.nextInt(cursor.getCount());//0~50 데이터개수로 바꾸기 51
            for(int j=0;j<i;j++){//중복제거
                if(iarr[i]==iarr[j]) i--;
            }
        }

    }
    public void setQuizText(int cnt){
        cursor.moveToPosition(iarr[cnt]);
        meanText.setText(cursor.getString(cursor.getColumnIndex("mean")));
        engText.setText(underline());
    }
    //답 체크
    public Boolean isResult(){
        if(answerText.getText().toString().equals(cursor.getString(cursor.getColumnIndex("result")))){
            answercnt++;
            return true;
        }else {
            return false;
        }
    }
    public String underline(){
        String content = cursor.getString(cursor.getColumnIndex("quiz"));
        String underline="";
        for(int i=0; i<cursor.getString(cursor.getColumnIndex("result")).length(); i++){
            underline+="_";
        }
        content=content.replace(cursor.getString(cursor.getColumnIndex("result")),underline);
        return content;
    }
}
