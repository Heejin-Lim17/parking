package kr.ac.gachon.parking.Customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*
import kr.ac.gachon.parking.MainActivity
import kr.ac.gachon.parking.R

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_signup_ok.setOnClickListener {
            Toast.makeText(this,"회원가입을 축하드립니다!", Toast.LENGTH_LONG).show()
            val signup_finish_intent= Intent(this, MainActivity::class.java)
            startActivity(signup_finish_intent)
        }
    }
}
