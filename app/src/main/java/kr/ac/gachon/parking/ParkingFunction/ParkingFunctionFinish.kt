package kr.ac.gachon.parking.ParkingFunction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_parking_function_finish.*
import kr.ac.gachon.parking.MainActivity
import kr.ac.gachon.parking.R

class ParkingFunctionFinish : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_function_finish)

        btn_func_ok.setOnClickListener {
            val func_finish_to_main= Intent(this, MainActivity::class.java)
            startActivity(func_finish_to_main)
        }
    }
}
