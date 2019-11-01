package kr.ac.gachon.parking.Group.SearchGroup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kr.ac.gachon.parking.Group.MainGroup.GroupItemSel
import kr.ac.gachon.parking.R

class SearchGroupAdapter(val context: Context): BaseAdapter() {
    private val sInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return SearchSel.values().size
    }

    override fun getItem(position: Int)= SearchSel.values()[position]

    override fun getItemId(position: Int)=position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val search_listview=convertView?:sInflater.inflate(R.layout.group_search_list,parent,false)
        search_listview.findViewById<TextView>(R.id.search_groupname).text=getItem(position).searchgroup

        return search_listview
    }

}