package com.example.fame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InputSetActivity extends AppCompatActivity {

    TextView cnt;
    Button upButton;
    Button downButton;
    Button backButton;
    Button finishButton;
    int count=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_set_actvity);

        cnt=findViewById(R.id.cnt);
        upButton=findViewById(R.id.upButton);
        downButton=findViewById(R.id.downButton);
        backButton=findViewById(R.id.backButton);
        finishButton=findViewById(R.id.finishButton);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<10) {
                    count++;
                    cnt.setText("" + count);
                }
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>1) {
                    count--;
                    cnt.setText("" + count);
                }
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("count",count);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
