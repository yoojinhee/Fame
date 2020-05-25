package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    TextView levelText;
    Button nextButton;

    int answercnt;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        Intent getIntent=getIntent();
        answercnt=getIntent.getIntExtra("answercnt",-1);

        levelText=findViewById(R.id.levelText);
        nextButton=findViewById(R.id.nextButton);

        if(answercnt<10){
            level="초급";
        }else if(answercnt<20){
            level="중급";
        }else{
            level="고급";
        }
        levelText.setText(level);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuizResultActivity.this,SelModeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
