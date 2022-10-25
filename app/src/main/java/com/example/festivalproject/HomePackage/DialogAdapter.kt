package com.example.festivalproject.HomePackage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.festivalproject.R

class DialogAdapter(
    context: Context,
    val list: Array<String>
) : BaseAdapter() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder = ViewHolder()
        val view: View

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.sort_area_item, null)
            viewHolder.textView = view.findViewById(R.id.sort_area_name)

            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            view = convertView
        }

        viewHolder.textView?.setText(list.get(position))
        return view
    }

    data class ViewHolder(var textView: TextView? = null)
}