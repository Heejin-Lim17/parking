package kr.ac.gachon.parking.ParkingFunction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_parking_function.*
import kr.ac.gachon.parking.MainActivity
import kr.ac.gachon.parking.R


class ParkingFunction : AppCompatActivity() {
    companion object {
        var hour=Hour.AM1
        var minute=Minute.min00
        var fee=Fee.fee00
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_function)

        //spinner 설정
        //시
        spin_hour.adapter=ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Hour.values().map{it.hour})
        spin_hour.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ParkingFunction.hour=Hour.values()[position]
            }

        }
        //분
        spin_minute.adapter=ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Minute.values().map{it.minute})
        spin_minute.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ParkingFunction.minute=Minute.values()[position]
            }
        }
        //요금
        spin_fee.adapter=ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Fee.values().map{it.fee})
        spin_fee.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ParkingFunction.fee=Fee.values()[position]
            }
        }

        btn_func_oks.setOnClickListener {
            /* 시간 또는 요금에 도달 시 알림 설정 */
            if(hour.hour.equals("")) {}

            val func_to_finish= Intent(this, ParkingFunctionFinish::class.java)
            startActivity(func_to_finish)
        }

        btn_func_cancel.setOnClickListener{
            val func_cancel=Intent(this, MainActivity::class.java)
            startActivity(func_cancel)
        }
    }
}
