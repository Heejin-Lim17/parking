//package kr.ac.gachon.parking.Group.MyGroup
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.Button
//import android.widget.TextView
//import kr.ac.gachon.parking.Group.MainGroup.GroupItemSel
//import kr.ac.gachon.parking.R
//
//class MyGroupAdapter(val context: Context): BaseAdapter() {
//    private val mInflater: LayoutInflater = LayoutInflater.from(context)
//    override fun getCount(): Int {
//        return MyGroupNameSel.values().size
//    }
//
//    override fun getItem(position: Int)= MyGroupNameSel.values()[position]
//
//    override fun getItemId(position: Int)=position.toLong()
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val list_mygroup=convertView?:mInflater.inflate(R.layout.activity_my_group_list,parent,false)
//        list_mygroup.findViewById<TextView>(R.id.list_mygroup).text=getItem(position).groupname
//        //mygroup_listview.findViewById<Button>(R.id.radioButton)
//        return list_mygroup
//    }
//
//}
