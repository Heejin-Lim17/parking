package kr.ac.gachon.parking.Group.MainGroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group.*
import kr.ac.gachon.parking.Group.GroupMakingActivity
import kr.ac.gachon.parking.Group.GroupSet.GroupSetFirst
import kr.ac.gachon.parking.Group.JoinGroup.JoinGroup
import kr.ac.gachon.parking.Group.MyGroup.MyGroupActivity


class GroupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(kr.ac.gachon.parking.R.layout.activity_group)



        var group_list_adapter= GroupItemAdapter(this)
        group_listview.adapter=group_list_adapter

        group_listview.setOnItemClickListener{_,_,position,_->
            var curItem=group_list_adapter.getItem(position)

            if ("${curItem.groupfun}"=="그룹 생성"){
                val group_making_intent= Intent(this, GroupMakingActivity::class.java)
                startActivity(group_making_intent)
            }
            if("${curItem.groupfun}"=="그룹 가입") {
                val group_making_intent = Intent(this, JoinGroup::class.java)
                startActivity(group_making_intent)
            }
            if("${curItem.groupfun}"=="나의 그룹") {
                val my_group_intent = Intent(this, MyGroupActivity::class.java)
                startActivity(my_group_intent)
            }
            if("${curItem.groupfun}"=="그룹 관리") {
                val group_set_intent = Intent(this, GroupSetFirst::class.java)
                startActivity(group_set_intent)
            }
        }

    }

}
