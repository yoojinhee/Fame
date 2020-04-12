package kr.hs.emirim.s2018s16.fame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main.setOnClickListener{

            startActivity(Intent(this@MainActivity, SelModeActivity::class.java)) //button 누르면 이동

        }

    }
}
