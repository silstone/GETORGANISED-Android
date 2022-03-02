package com.getorganized.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.MainActivity
import com.getorganized.activity.ScheduleTask
import com.getorganized.model_classes.SubTask
import com.getorganized.utils.Constant

class Schedule_SubListAdapter() : RecyclerView.Adapter<Schedule_SubListAdapter.ViewHolder>() {

    lateinit var mList: List<SubTask>
    lateinit var main: ScheduleTask
    private var mContext: Context? = null
    var color: String? = ""
    private var taskname: String? = ""
    val constant = Constant()

    constructor(list: List<SubTask>, context: Context) : this() {
        mList = list
        this.taskname = taskname
        this.mContext = context;
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_sub_list_adapter, parent, false)
        main = ScheduleTask()

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      //  val mList.get(position) = mList[position]
       
        var status = mList.get(position).getTask_status().toString().trim()
        if (status.equals(constant.completed)) {
        }else
          /*  if (status.equals(constant.Unscheduled)){
            holder.inbox_view.visibility = View.VISIBLE
            holder.main_layout.visibility = View.GONE
            holder.inbox_text.setText(mList.get(position).getTitle())
        }
        else if (status.equals(constant.Scheduled)) */
        {
            holder.task_txt.text = mList.get(position).getTitle()
            holder.task_time.text = mList.get(position).getStart_time() + "-" + mList.get(position).getEnd_time()
            holder.task_start_time.text = mList.get(position).getStart_time()


            color = mList.get(position).getColor()
            if (color.equals("shade_0")) {

                holder.text_background.setBackground(mContext!!.resources.getDrawable(R.drawable.shade_0_txt))
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE

            }else if (color.equals("shade_1")) {

                holder.text_background.setBackground(mContext!!.resources.getDrawable(R.drawable.shade_1_txt))
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE

            } else if (color.equals("shade_2")) {
                holder.text_background.setBackground(mContext!!.resources.getDrawable(R.drawable.shade_2_txt))
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_3")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_3_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_4")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_4_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_5")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_5_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_6")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_6_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_7")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_7_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_8")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_8_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_9")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_9_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_10")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_10_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_11")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_11_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_12")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_12_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_13")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_13_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_14")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_14_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_15")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(
                        R.drawable.shade_15_txt
                    )
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else if (color.equals("shade_16")) {
                holder.text_background.setBackground(
                    mContext!!.resources.getDrawable(R.drawable.shade_16_txt)
                )
                holder.inbox_view.visibility = View.GONE
                holder.main_layout.visibility = View.VISIBLE
            } else {

            }


        }


        holder.text_background.setOnClickListener {

                if (mContext is ScheduleTask) {
                    (mContext as ScheduleTask).showDetail(
                        mList[position].getNodeid().toString(),
                        mList[position].getTitle().toString(),
                        color.toString()
                    )
                }
        }

        holder.done.setOnClickListener {

            if (mContext is ScheduleTask) {
                (mContext as ScheduleTask).showDetail(
                    mList[position].getNodeid().toString(),
                    mList[position].getTitle().toString(),
                    color.toString()
                )
            }
        }
        holder.delete.setOnClickListener {

            if (mContext is ScheduleTask) {
                (mContext as ScheduleTask).showDetail(
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


        val task_date: TextView = itemView.findViewById(R.id.task_date)
        val task_count: TextView = itemView.findViewById(R.id.task_count)
        val task_time: TextView = itemView.findViewById(R.id.task_time)
        val task_txt: TextView = itemView.findViewById(R.id.task_txt)
        val task_start_time: TextView = itemView.findViewById(R.id.task_start_time)
        val inbox_text: TextView = itemView.findViewById(R.id.card_text)
        val inbox_view: CardView = itemView.findViewById(R.id.inbox_view)
        val text_background: CardView = itemView.findViewById(R.id.text_background)
        

        val done: LinearLayout = itemView.findViewById(R.id.done)
        val delete: LinearLayout = itemView.findViewById(R.id.delete)
        val main_layout: LinearLayout = itemView.findViewById(R.id.main_layout)


    }
}