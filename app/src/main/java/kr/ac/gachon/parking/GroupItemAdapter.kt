package kr.ac.gachon.parking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class GroupItemAdapter(val context: Context):BaseAdapter() {
    private val mInflater:LayoutInflater= LayoutInflater.from(context)
    override fun getCount(): Int {
        return GroupItemSel.values().size
    }

    override fun getItem(position: Int)=GroupItemSel.values()[position]

    override fun getItemId(position: Int)=position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val group_listview=convertView?:mInflater.inflate(R.layout.list_item_group,parent,false)
        group_listview.findViewById<TextView>(R.id.group_fun).text=getItem(position).groupfun
        group_listview.findViewById<TextView>(R.id.group_fun_info).text=getItem(position).groupinfo
        return group_listview
    }

}
