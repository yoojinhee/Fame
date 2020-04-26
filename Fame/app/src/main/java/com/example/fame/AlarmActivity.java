package com.example.fame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //알림음 재생

        this.mediaPlayer= MediaPlayer.create(this, R.raw.alarm);
        this.mediaPlayer.start();


        findViewById(R.id.btnClose).setOnClickListener(mClickListenr);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        //MediaPlayer release()
        if(this.mediaPlayer !=null){
            this.mediaPlayer.release();
            this.mediaPlayer=null;
        }
    }
    //알람 종료
    private void close(){
        if(this.mediaPlayer.isPlaying()){
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer=null;
        }
        finish();
    }
    View.OnClickListener mClickListenr=new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btnClose:
                    //알람종료
                    close();

                    break;
            }
        }
    };
}
