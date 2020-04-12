package kr.hs.emirim.s2018s16.fame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sel_mode.*

class SelModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sel_mode)

        effortmode.setOnClickListener{

            startActivity(Intent(this@SelModeActivity, CategoryActivity::class.java)) //button 누르면 이동

        }
    }
}
