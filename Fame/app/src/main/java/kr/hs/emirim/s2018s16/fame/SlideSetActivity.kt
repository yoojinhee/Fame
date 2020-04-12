package kr.hs.emirim.s2018s16.fame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_slide_set.*
import kotlinx.android.synthetic.main.activity_word_set.*

class SlideSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_set)

        button2.setOnClickListener{

            startActivity(Intent(this@SlideSetActivity, WordSetActivity::class.java)) //button 누르면 이동

        }
        button5.setOnClickListener{

            startActivity(Intent(this@SlideSetActivity, SliderepeatSetActivity::class.java)) //button 누르면 이동

        }
    }


}
