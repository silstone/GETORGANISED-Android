package com.getorganized.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R


class TaskListAdapter(val items: HashMap<Int, String>, val context: Context) :
    RecyclerView.Adapter<MyViewHolder>() {

    var row_index=0
    val typeface = ResourcesCompat.getFont(context, R.font.poppins_semi_bold)
    val typeface2 = ResourcesCompat.getFont(context, R.font.poppins_reguler)


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.task_list_adapter, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //  holder?.listname. = items.get(position)
        holder.name.setText(items.get(position))
        if (position == 0) {

            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.txt_gray))
        } else if (position == 1) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_12))
        } else if (position == 2) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_13))
        } else if (position == 3) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_14))
        }


        holder.name.setOnClickListener {
            row_index=position;
            notifyDataSetChanged();
        }



        if(row_index==position){
            holder.name.setTypeface(typeface)
            holder.check_btn.visibility = View.VISIBLE
            holder.task_name_layout.setBackground(context.resources.getDrawable(R.drawable.schedule_txt_background))

        }
        else
        {
            holder.name.setTypeface(typeface2)
            holder.check_btn.visibility = View.GONE
            holder.task_name_layout.setBackground(null)

        }

    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    val name = view.findViewById(R.id.name) as TextView
    val task_name_layout = view.findViewById(R.id.task_name_layout) as RelativeLayout
    val bottomview = view.findViewById(R.id.bottomview) as View
    val check_btn = view.findViewById(R.id.check_btn) as ImageView
}
