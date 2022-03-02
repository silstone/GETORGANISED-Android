package com.getorganized.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R


class DateListAdapter(val items: MutableList<String> = ArrayList(), val context: Context) :
    RecyclerView.Adapter<dateViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dateViewHolder {
        return dateViewHolder(
            LayoutInflater.from(context).inflate(R.layout.date_list_adapter, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: dateViewHolder, position: Int) {
        //  holder?.listname. = items.get(position)
        holder.date_no.setText(items.get(position))
        

    }
}

class dateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val date_no = view.findViewById(R.id.date_no) as TextView
    val month_txt = view.findViewById(R.id.month_txt) as TextView
    
}
