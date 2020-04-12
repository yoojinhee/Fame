package kr.hs.emirim.s2018s16.fame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_main.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        slidebutton.setOnClickListener{

            startActivity(Intent(this@CategoryActivity, WordSetActivity::class.java)) //button 누르면 이동

        }

        previousbutton.setOnClickListener{

            startActivity(Intent(this@CategoryActivity, SelModeActivity::class.java)) //button 누르면 이동

        }
        nextbutton.setOnClickListener{

            startActivity(Intent(this@CategoryActivity, WordSetActivity::class.java)) //button 누르면 이동

        }
    }

}
