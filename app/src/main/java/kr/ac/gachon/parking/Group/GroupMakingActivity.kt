package kr.ac.gachon.parking.Group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group_making.*
import kr.ac.gachon.parking.Group.MainGroup.GroupActivity
import kr.ac.gachon.parking.R

class GroupMakingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_making)

        btn_signup_ok.setOnClickListener {
            val gsign_to_main_intent= Intent(this, GroupActivity::class.java)
            startActivity(gsign_to_main_intent)
        }

        btn_signup_cancel.setOnClickListener {
            val gsign_to_main_intent= Intent(this, GroupActivity::class.java)
            startActivity(gsign_to_main_intent)
        }

    }
}
