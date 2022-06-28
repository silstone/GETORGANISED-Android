package com.getorganized.adapters

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.model_classes.SubTask
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref

class Completed_Adapter() : RecyclerView.Adapter<Completed_Adapter.ViewHolder>() {

    lateinit var mList: List<SubTask>
    private var context: Context? = null
    val sharedPref = SharedPref()
    val constant = Constant()

    constructor(list: List<SubTask>, context: Context) : this() {
        mList = list
        this.context = context;
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.completed_adapter, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val s_yesterday = sharedPref.get_value(context!!, constant.yesterday)
        val s_today = sharedPref.get_value(context!!, constant.today)
        val s_tomorrow = sharedPref.get_value(context!!, constant.tomorrow)


        val ItemsViewModel = mList[position]
        //  holder.three_dots_img.setImageResource(ItemsViewModel.image)
        holder.task_name.text = ItemsViewModel.getList_name()
        holder.title.setText(ItemsViewModel.getTitle())
        holder.title.setPaintFlags(holder.title.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        var start = ItemsViewModel.getStart_time().toString().trim()
        var end = ItemsViewModel.getEnd_time().toString().trim()
        holder.time.setText(start + "-" + end)

        var date = ItemsViewModel.getDate()
        if (date.equals("null") || date.equals("")) {
            holder.txt_date.visibility = View.GONE
        } else {
            holder.txt_date.setText(ItemsViewModel.getDate())
        }
        constant.complete_time = mList.get(position).getDate().toString().trim()

        if (constant.complete_time.equals(s_yesterday)) {
            holder.txt_date.setText("Yesterday")
        } else if (constant.complete_time.equals(s_today)) {
            holder.txt_date.setText("Today")
        } else if (constant.complete_time.equals(s_tomorrow)) {
            holder.txt_date.setText("Tomorrow")
        } else {
            holder.txt_date.setText(mList.get(position).getDate().toString().trim())
        }


        val color = ItemsViewModel.getColor()
        Log.e("color", color.toString())

        if (color.equals("shade_0")) {
            holder.title.setTextColor(context!!.resources.getColor(R.color.shade_0))
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_0))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_0))
        }
        if (color.equals("shade_1")) {
            holder.title.setTextColor(context!!.resources.getColor(R.color.shade_1))
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_1))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_1))
        }
        if (color.equals("shade_2")) {
            holder.title.setTextColor(context!!.resources.getColor(R.color.shade_2))
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_2))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_2))
        }
        if (color.equals("shade_3")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_3
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_3))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_3))
        }
        if (color.equals("shade_4")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_4
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_4))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_4))
        }
        if (color.equals("shade_5")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_5
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_5))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_5))
        }
        if (color.equals("shade_6")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_6
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_6))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_6))
        }
        if (color.equals("shade_7")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_7
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_7))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_7))
        }
        if (color.equals("shade_8")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_8
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_8))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_8))
        }
        if (color.equals("shade_9")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_9
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_9))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_9))
        }
        if (color.equals("shade_10")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_10
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_10))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_10))
        }
        if (color.equals("shade_11")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_11
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_11))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_11))
        }
        if (color.equals("shade_12")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_12
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_12))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_12))
        }
        if (color.equals("shade_13")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_13
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_13))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_13))
        }
        if (color.equals("shade_14")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_14
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_14))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_14))
        }
        if (color.equals("shade_15")) {
            holder.title.setTextColor(
                context!!.resources.getColor(
                    R.color.shade_15
                )
            )
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_15))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_15))
        }
        if (color.equals("shade_16")) {
            holder.title.setTextColor(context!!.resources.getColor(R.color.shade_16))
            holder.task_name.setTextColor(context!!.resources.getColor(R.color.shade_16))
            holder.time.setTextColor(context!!.resources.getColor(R.color.shade_16))
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txt_date: TextView = itemView.findViewById(R.id.txt_date)
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)
        val task_name: TextView = itemView.findViewById(R.id.task_name)


    }
}