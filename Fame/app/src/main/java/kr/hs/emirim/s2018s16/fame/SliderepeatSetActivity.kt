package kr.hs.emirim.s2018s16.fame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_slide_set.*
import kotlinx.android.synthetic.main.activity_sliderepeat_set.*

class SliderepeatSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliderepeat_set)


        val textView5=findViewById<TextView>(R.id.textView5)
        val button6=findViewById<Button>(R.id.button6)
        val button7=findViewById<Button>(R.id.button7)
        var counter=0;

        button6.setOnClickListener {
            counter++;
            textView5.text=counter.toString()
        }

        button7.setOnClickListener{
            if(counter>0)
                counter--;
            textView5.text=counter.toString()

        }
        button8.setOnClickListener{

            startActivity(Intent(this@SliderepeatSetActivity, SlideSetActivity::class.java)) //button 누르면 이동

        }

    }
}
