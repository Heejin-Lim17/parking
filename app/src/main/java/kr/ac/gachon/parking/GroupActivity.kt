package kr.ac.gachon.parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        var group_list_adapter=GroupItemAdapter(this)
        group_listview.adapter=group_list_adapter

        group_listview.setOnItemClickListener{parent,view,position,id->
            var curItem=group_list_adapter.getItem(position)

            if ("${curItem.groupfun}"=="그룹 생성"){
                val group_making_intent= Intent(this,GroupMakingActivity::class.java)
                startActivity(group_making_intent)
            }

        }
    }

}