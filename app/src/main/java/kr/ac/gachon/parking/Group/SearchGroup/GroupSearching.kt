package kr.ac.gachon.parking.Group.SearchGroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_group_searching.*
import kr.ac.gachon.parking.Customer.SignupActivity
import kr.ac.gachon.parking.Group.MainGroup.GroupItemAdapter
import kr.ac.gachon.parking.R

class GroupSearching : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_searching)

//        var group_search_adapter= SearchGroupAdapter(this)
//        search_list.adapter=group_search_adapter

        search_res.setOnClickListener {
            val gsignup_intent= Intent(this, GroupSignupActivity::class.java)
            startActivity(gsignup_intent)
        }
    }
}
