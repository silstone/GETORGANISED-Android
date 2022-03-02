 package com.getorganized.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.MainActivity
import com.getorganized.activity.ScheduleTask
import com.getorganized.model_classes.TaskList



class AddListAdapter(val items: ArrayList<TaskList>, val context: Context) :
    RecyclerView.Adapter<ViewMyHolder>() {

    lateinit var scheduleTask: ScheduleTask


    var row_index=1000
    val typeface = ResourcesCompat.getFont(context, R.font.poppins_semi_bold)
    val typeface2 = ResourcesCompat.getFont(context, R.font.poppins_reguler)


    override fun getItemCount(): Int {
        return items.size
    }


    init {
        scheduleTask = context as ScheduleTask
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMyHolder {
        return ViewMyHolder(
            LayoutInflater.from(context).inflate(R.layout.add_list_adapter, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewMyHolder, position: Int) {

        holder.name.setText(items.get(position).getname())


        val color = items.get(position).getcolorname()

        if (color.equals("shade_0")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_0
                )
            )
        }
        if (color.equals("shade_1")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_1
                )
            )
        }
        if (color.equals("shade_2")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_2
                )
            )
        }
        if (color.equals("shade_3")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_3
                )
            )
        }
        if (color.equals("shade_4")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_4
                )
            )
        }
        if (color.equals("shade_5")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_5
                )
            )
        }
        if (color.equals("shade_6")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_6
                )
            )
        }
        if (color.equals("shade_7")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_7
                )
            )
        }
        if (color.equals("shade_8")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_8
                )
            )
        }
        if (color.equals("shade_9")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_9
                )
            )
        }
        if (color.equals("shade_10")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_10
                )
            )
        }
        if (color.equals("shade_11")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_11
                )
            )
        }
        if (color.equals("shade_12")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_12
                )
            )
        }
        if (color.equals("shade_13")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_13
                )
            )
        }
        if (color.equals("shade_14")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_14
                )
            )
        }
        if (color.equals("shade_15")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_15
                )
            )
        }
        if (color.equals("shade_16")) {
            holder.bottomview.setBackgroundColor(
                context.resources.getColor(
                    R.color.shade_16
                )
            )
        }


        holder.name.setOnClickListener {
            row_index=position;
            notifyDataSetChanged();
        }



        if(row_index==position){
            holder.name.setTypeface(typeface)
            holder.name.setTextColor(context.resources.getColor(R.color.black))
            holder.check_btn.visibility = View.VISIBLE
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.schedule_txt_background))
            scheduleTask.showListName(items.get(position).getname().toString(), items.get(position).getcolorname().toString())
        }
        else
        {
            holder.name.setTypeface(typeface2)
            holder.name.setTextColor(context.resources.getColor(R.color.black))
            holder.check_btn.visibility = View.GONE
            holder.name_layout.setBackground(null)

        }

    }



}


class ViewMyHolder(view: View) : RecyclerView.ViewHolder(view) {



    val name = view.findViewById(R.id.name) as TextView
    val name_layout = view.findViewById(R.id.task_name_layout) as RelativeLayout
    val bottomview = view.findViewById(R.id.bottomview) as View
    val check_btn = view.findViewById(R.id.check_btn) as ImageView

}


