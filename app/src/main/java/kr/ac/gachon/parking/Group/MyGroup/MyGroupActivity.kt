package kr.ac.gachon.parking.Group.MyGroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_my_group.*
import kotlinx.android.synthetic.main.activity_signup.*
import kr.ac.gachon.parking.Group.MainGroup.GroupActivity
import kr.ac.gachon.parking.Group.MainGroup.GroupItemAdapter
import kr.ac.gachon.parking.MainActivity
import kr.ac.gachon.parking.R

class MyGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_group)


        val linearLayout = findViewById(R.id.linearLayout) as LinearLayout
        val radioGroup = RadioGroup(this)
        val radioGroup2 = RadioGroup(this)
        val textView = TextView(this)

        radioGroup.orientation = RadioGroup.VERTICAL

        //array에 그룹명 입력
        val options = arrayOf("Option 2", "Option 3", "Option 4", "Option 5")

        for(option in options){
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }

        linearLayout.addView(radioGroup)
        linearLayout.addView(radioGroup2)
        linearLayout.addView(textView)

        btn_fee_finish.setOnClickListener {
            Toast.makeText(this,"주차 요금이 입력되었습니다.", Toast.LENGTH_LONG).show()
            val fee_finish_intent= Intent(this, GroupActivity::class.java)
            startActivity(fee_finish_intent)
        }
    }

}
