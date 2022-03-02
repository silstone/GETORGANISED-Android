package com.getorganized.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.MainActivity
import com.getorganized.model_classes.TaskList


class DashboardListAdapter(val items: ArrayList<TaskList>, val context: Context) :
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
        holder.name.setText(items.get(position).getname())
        if (position == 0) {
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.inbox_background))
        }

        val color = items.get(position).getcolorname()

         if(color.equals("shade_0")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_0
             )
         )}
         if(color.equals("shade_1")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_1
             )
         )}
         if(color.equals("shade_2")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_2
             )
         )}
         if(color.equals("shade_3")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_3
             )
         )}
         if(color.equals("shade_4")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_4
             )
         )}
         if(color.equals("shade_5")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_5
             )
         )}
         if(color.equals("shade_6")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_6
             )
         )}
         if(color.equals("shade_7")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_7
             )
         )}
         if(color.equals("shade_8")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_8
             )
         )}
         if(color.equals("shade_9")){holder.bottomview.setBackgroundColor(
             context.resources.getColor(
                 R.color.shade_9
             )
         )}
        if(color.equals("shade_10")){holder.bottomview.setBackgroundColor(
            context.resources.getColor(
                R.color.shade_10
            )
        )}
        if(color.equals("shade_11")){holder.bottomview.setBackgroundColor(
            context.resources.getColor(
                R.color.shade_11
            )
        )}
        if(color.equals("shade_12")){holder.bottomview.setBackgroundColor(
            context.resources.getColor(
                R.color.shade_12
            )
        )}
        if(color.equals("shade_13")){holder.bottomview.setBackgroundColor(
            context.resources.getColor(
                R.color.shade_13
            )
        )}
        if(color.equals("shade_14")){holder.bottomview.setBackgroundColor(
            context.resources.getColor(
                R.color.shade_14
            )
        )}
        if(color.equals("shade_15")){holder.bottomview.setBackgroundColor(
            context.resources.getColor(
                R.color.shade_15
            )
        )}
        if(color.equals("shade_16")){holder.bottomview.setBackgroundColor(
            context.resources.getColor(
                R.color.shade_16
            )
        )}

        /* else if (position == 1) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_12))
        } else if (position == 2) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_13))
        } else if (position == 3) {
            holder.bottomview.setBackgroundColor(context.resources.getColor(R.color.shade_14))
        }*/


        holder.name.setOnClickListener {

            val color = items.get(position).getcolorname()
            if(color.equals("shade_0")){
                holder.name.setTextColor(context.resources.getColor(R.color.white))
                holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.inbox_background))

                mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_1")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_1_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_2")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_2_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_3")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_3_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())

         }
         if(color.equals("shade_4")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_4_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_5")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_5_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_6")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_6_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_7")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_7_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_8")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_8_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
         if(color.equals("shade_9")){
             holder.name.setTextColor(context.resources.getColor(R.color.white))
             holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_9_bckgrd))
             mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
         }
        if(color.equals("shade_10")){
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_10_bckgrd))
            mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
        }
        if(color.equals("shade_11")){
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_11_bckgrd))
            mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
        }
        if(color.equals("shade_12")){
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_12_bckgrd))
            mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
        }
        if(color.equals("shade_13")){
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_13_bckgrd))
            mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
        }
        if(color.equals("shade_14")){
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_14_bckgrd))
            mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
        }
        if(color.equals("shade_15")){
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_15_bckgrd))
            mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
        }
        if(color.equals("shade_16")){
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.name_layout.setBackground(context.resources.getDrawable(R.drawable.shade_16_bckgrd))
            mainActivity.showtask(items.get(position).getname().toString().trim(),color.toString())
        }

        }


    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {



    val name = view.findViewById(R.id.name) as TextView
    val name_layout = view.findViewById(R.id.name_layout) as GridLayout
    val bottomview = view.findViewById(R.id.bottomview) as View


}

