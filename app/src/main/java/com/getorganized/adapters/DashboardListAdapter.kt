package com.getorganized.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.MainActivity



class DashboardListAdapter(val items: HashMap<Int, String>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {
lateinit var mainActivity: MainActivity

    override fun getItemCount(): Int {
        return items.size
    }

    init {
        mainActivity = context as MainActivity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.dashboard_list_adapter, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  holder?.listname. = items.get(position)
        holder.name.setText(items.get(position))
        if (position == 0) {
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.inbox_background))
        } else if (position == 1) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_12))
        } else if (position == 2) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_13))
        } else if (position == 3) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_14))
        }


        holder.name.setOnClickListener {
            if (position == 0) {
                holder.name.setTextColor(context.resources.getColor(R.color.white))
                holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.inbox_background))

                mainActivity.second_layout.visibility = View.VISIBLE
                mainActivity.first_layout.visibility = View.GONE
                mainActivity.third_layout.visibility = View.GONE
                mainActivity.swipe_layout.visibility = View.VISIBLE

               // mainActivity.second_layout.visibility = View.VISIBLE
            } else if (position == 1) {
                holder.name.setTextColor(context.resources.getColor(R.color.white))
                holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_12_bckgrd))

                mainActivity.third_layout.visibility = View.VISIBLE
                mainActivity.first_layout.visibility = View.GONE
                mainActivity.second_layout.visibility = View.GONE
                mainActivity.swipe_layout.visibility = View.GONE

            } else if (position == 2) {
                holder.name.setTextColor(context.resources.getColor(R.color.white))
                holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_13_bckgrd))

                mainActivity.third_layout.visibility = View.VISIBLE
                mainActivity.first_layout.visibility = View.GONE
                mainActivity.second_layout.visibility = View.GONE
                mainActivity.swipe_layout.visibility = View.GONE
            } else if (position == 3) {
                holder.name.setTextColor(context.resources.getColor(R.color.white))
                holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_14_bckgrd))

                mainActivity.third_layout.visibility = View.VISIBLE
                mainActivity.first_layout.visibility = View.GONE
                mainActivity.second_layout.visibility = View.GONE
                mainActivity.swipe_layout.visibility = View.GONE

            }else{
               /* holder.name.setTextColor(context.resources.getColor(R.color.white))
                holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_12_bckgrd))*/
            }
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    val name = view.findViewById(R.id.name) as TextView
    val name_layout = view.findViewById(R.id.name_layout) as RelativeLayout
    val bottomview = view.findViewById(R.id.bottomview) as View


}

