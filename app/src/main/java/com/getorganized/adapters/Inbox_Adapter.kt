package com.getorganized.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.MainActivity
import com.getorganized.model_classes.SubTask

class Inbox_Adapter(): RecyclerView.Adapter<Inbox_Adapter.ViewHolder>() {

    lateinit var mList: List<SubTask>
    lateinit var main: MainActivity
    private var mContext: Context? = null

    constructor(list: List<SubTask>, context: Context) : this() {
        mList = list
        this.mContext = context;
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.inbox_adapter, parent, false)
        main = MainActivity()

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        //  holder.three_dots_img.setImageResource(ItemsViewModel.image)
        holder.sub_text.text = ItemsViewModel.getTitle()
        Log.e("sub_text",  ItemsViewModel.getTitle().toString())

       // var color= ""
       // val color_name = ItemsViewModel.getcolorname()


          holder.sub_text.setOnClickListener {
              if (mContext is MainActivity) {
                  (mContext as MainActivity).showDetail(mList[position].getNodeid().toString(), mList[position].getTitle().toString(),"shade_0")
              }

          }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

       val sub_text: TextView = itemView.findViewById(R.id.sub_text)

    }
}