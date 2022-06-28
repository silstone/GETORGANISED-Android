package com.getorganized.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.MainActivity
import com.getorganized.model_classes.SubTask

class SubListAdapter(): RecyclerView.Adapter<SubListAdapter.ViewHolder>() {

    lateinit var mList: List<SubTask>
    lateinit var main: MainActivity
    private var mContext: Context? = null
    var color: String?= ""
    private  var taskname: String?= ""


    constructor(list: List<SubTask>, context: Context) : this() {
        mList = list
        this.taskname = taskname
        this.color = color
        this.mContext = context;
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_list_adapter, parent, false)
        main = MainActivity()

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        //  holder.three_dots_img.setImageResource(ItemsViewModel.image)



        holder.subtask_txt.text = ItemsViewModel.getTitle()   //+"\n"+ ItemsViewModel.getList_name() +" - "+ItemsViewModel.getEnd_time()
        holder.subtask_time.text = ItemsViewModel.getStart_time()+"-"+ ItemsViewModel.getEnd_time()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val subtask_time: TextView = itemView.findViewById(R.id.start_end_time)
        val subtask_txt: TextView = itemView.findViewById(R.id.sub_txt)

    }
}