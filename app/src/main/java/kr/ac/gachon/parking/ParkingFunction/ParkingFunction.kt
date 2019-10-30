package kr.ac.gachon.parking.ParkingFunction

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_parking_function.*
import kotlinx.android.synthetic.main.activity_parking_function.view.*
import kr.ac.gachon.parking.MainActivity
import kr.ac.gachon.parking.R


class ParkingFunction : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(kr.ac.gachon.parking.R.layout.activity_parking_function)

        //spinner 설정
        //시
        spin_hour.adapter=ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Hour.values().map{it.hour})
        spin_hour.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val pageView = findViewById<Spinner>(R.id.spin_hour)

                val hour=Hour.values()[position]

//                pageView.spin_hour.text = hour.hour
//                val hour=Hour.values()[position]

            }
        }
        //분
        spin_minute.adapter=ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Minute.values().map{it.minute})
        spin_minute.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val minute=Minute.values()[position]
            }
        }
        //요금
        spin_fee.adapter=ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Fee.values().map{it.fee})
        spin_fee.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val fee=Fee.values()[position]
            }
        }

        btn_func_ok.setOnClickListener {
            val func_to_finish= Intent(this, ParkingFunctionFinish::class.java)
            startActivity(func_to_finish)
        }

        btn_func_cancel.setOnClickListener{
            val func_cancel=Intent(this, MainActivity::class.java)
            startActivity(func_cancel)
        }
    }

}
