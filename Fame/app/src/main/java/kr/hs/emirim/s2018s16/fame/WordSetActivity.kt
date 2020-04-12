package kr.hs.emirim.s2018s16.fame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_word_set.*

class WordSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_set)

        val num=findViewById<TextView>(R.id.num)
        val button=findViewById<Button>(R.id.button)
        val Downbutton=findViewById<Button>(R.id.Downbutton)
        var counter=0;

        button.setOnClickListener {
            counter++;
            num.text=counter.toString()
        }

        Downbutton.setOnClickListener{
            if(counter>0)
            counter--;
            num.text=counter.toString()

        }
        button3.setOnClickListener{

            startActivity(Intent(this@WordSetActivity, CategoryActivity::class.java)) //button 누르면 이동

        }
        button4.setOnClickListener{

            startActivity(Intent(this@WordSetActivity, SlideSetActivity::class.java)) //button 누르면 이동

        }
    }





}
