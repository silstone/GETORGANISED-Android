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

class Subtask_Adapter(): RecyclerView.Adapter<Subtask_Adapter.ViewHolder>() {

    lateinit var mList: List<SubTask>
    private var mContext: Context? = null
      var color: String?= ""
    private  var taskname: String?= ""

    constructor(list: List<SubTask>,taskname: String, color: String, context: Context) : this() {
        mList = list
        this.taskname = taskname
        this.color = color
        this.mContext = context;
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.subtask_adapter, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        //  holder.three_dots_img.setImageResource(ItemsViewModel.image)



        holder.subtask_txt.text = ItemsViewModel.getTitle()   //+"\n"+ ItemsViewModel.getList_name() +" - "+ItemsViewModel.getEnd_time()
        holder.subtask_time.text = ItemsViewModel.getDate()

        if(color.equals("shade_1")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_1_txt
            )
        )}
        if(color.equals("shade_2")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_2_txt
            )
        )}
        if(color.equals("shade_3")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_3_txt
            )
        )}
        if(color.equals("shade_4")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_4_txt
            )
        )}
        if(color.equals("shade_5")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_5_txt
            )
        )}
        if(color.equals("shade_6")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_6_txt
            )
        )}
        if(color.equals("shade_7")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_7_txt
            )
        )}
        if(color.equals("shade_8")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_8_txt
            )
        )}
        if(color.equals("shade_9")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_9_txt
            )
        )}
        if(color.equals("shade_10")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_10_txt
            )
        )}
        if(color.equals("shade_11")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_11_txt
            )
        )}
        if(color.equals("shade_12")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_12_txt
            )
        )}
        if(color.equals("shade_13")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_13_txt
            )
        )}
        if(color.equals("shade_14")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_14_txt
            )
        )}
        if(color.equals("shade_15")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_15_txt
            )
        )}
        if(color.equals("shade_16")){holder.subtask_txt.setBackground(
            mContext!!.resources.getDrawable(R.drawable.shade_16_txt
            )
        )}




        holder.subtask_txt.setOnClickListener {
            if (mContext is MainActivity) {
                (mContext as MainActivity).showDetail(
                    mList[position].getNodeid().toString(),
                    mList[position].getTitle().toString(),
                    color.toString()
                )
            }

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val subtask_time: TextView = itemView.findViewById(R.id.subtask_time)
        val subtask_txt: TextView = itemView.findViewById(R.id.subtask_txt)

    }
}