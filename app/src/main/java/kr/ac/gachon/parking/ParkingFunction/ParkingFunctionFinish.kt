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

        // TextView를 스피너로 입력받은 값으로 함
        text_hour.setText(ParkingFunction.hour.hour)
        text_minute.setText(ParkingFunction.minute.minute)
        text_fee.setText(ParkingFunction.fee.fee)

        btn_func_ok.setOnClickListener {
            val func_finish_to_main= Intent(this, MainActivity::class.java)
            startActivity(func_finish_to_main)
        }
    }
}
