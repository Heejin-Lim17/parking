package kr.ac.gachon.parking.Group.SearchGroup

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_group_signup.*

import android.view.View
import android.widget.Toast
import android.R
import android.content.Intent
import kr.ac.gachon.parking.Customer.SignupActivity
import kr.ac.gachon.parking.Group.MainGroup.GroupActivity


class GroupSignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(kr.ac.gachon.parking.R.layout.activity_group_signup)

        val dialog = AlertDialog.Builder(this@GroupSignupActivity)
            .setTitle("메뉴 예약")
            .setMessage("이 메뉴를 예약해드릴까요?")
            .setPositiveButton("예", {dialog, which ->
                val decide_intent=Intent(this, GroupActivity::class.java)
                startActivity(decide_intent)
            })
            .setNegativeButton("아니요", {dialog, which -> //startActivity(Intent(this@MenuActivity, MenuActivity::class.java))
            })
            .create()
        dialog.show()

    }


}
