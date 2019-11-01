package kr.ac.gachon.parking.Group.MyGroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_my_group.*
import kr.ac.gachon.parking.Group.MainGroup.GroupItemAdapter
import kr.ac.gachon.parking.R

class MyGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_group)

        var mygroup_list_adapter= MyGroupAdapter(this)
        list_mygroup.adapter=mygroup_list_adapter
    }
}
