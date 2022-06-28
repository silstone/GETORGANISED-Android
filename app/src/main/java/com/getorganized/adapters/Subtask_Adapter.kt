package com.getorganized.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.MainActivity
import com.getorganized.model_classes.SubTask
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref
import java.text.SimpleDateFormat
import java.util.*

class Subtask_Adapter() : RecyclerView.Adapter<Subtask_Adapter.ViewHolder>() {

    lateinit var mList: List<SubTask>
    private var mContext: Context? = null
    var color: String? = ""
    private var taskname: String? = ""
    val constant = Constant()

    val sharedPref = SharedPref()

    constructor(list: List<SubTask>, taskname: String, color: String, context: Context) : this() {
        mList = list
        this.taskname = taskname
        this.color = color
        this.mContext = context;
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.subtask_adapter, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        //  holder.three_dots_img.setImageResource(ItemsViewModel.image)

        val s_yesterday = sharedPref.get_value(mContext!!, constant.yesterday)
        val s_today = sharedPref.get_value(mContext!!, constant.today)
        val s_tomorrow = sharedPref.get_value(mContext!!, constant.tomorrow)


        /////////////////////////////////////////////////////////////////////////////////////////////start
        if (ItemsViewModel.getDate().equals("")) {

            if (constant.b_Unscheduled == true) {
                holder.subtask_time.visibility = View.GONE
            } else {
                holder.subtask_time.visibility = View.VISIBLE
                holder.subtask_time.setText(mContext?.resources?.getString(R.string.unscheduled))
                constant.b_Unscheduled = true
            }
        } else {
            val current_date: Date = SimpleDateFormat("MMM dd, yyyy").parse(s_today)
            val value_date: Date = SimpleDateFormat("MMM dd, yyyy").parse(ItemsViewModel.getDate().toString().trim())


            if (constant.b_time.equals("")) {
                constant.b_time = ItemsViewModel.getDate().toString().trim()


                if (current_date > (value_date)) {
                    holder.alert_icon.visibility = View.VISIBLE
                } else {
                    holder.alert_icon.visibility = View.GONE
                }
                if (constant.b_time.equals(s_yesterday)) {

                    holder.subtask_time.setText(
                        "Yesterday," + ItemsViewModel.getDate().toString().trim()
                    )
                } else if (constant.b_time.equals(s_today)) {
                    holder.subtask_time.setText(
                        "Today," + ItemsViewModel.getDate().toString().trim()
                    )
                } else if (constant.b_time.equals(constant.tomorrow.toString().trim())) {
                    holder.subtask_time.setText(
                        "Tomorrow," + ItemsViewModel.getDate().toString().trim()
                    )
                } else {
                    holder.subtask_time.setText(ItemsViewModel.getDate().toString().trim())
                }


            } else {
                if (ItemsViewModel.getDate().toString().trim().equals(constant.b_time)) {
                    holder.subtask_time.visibility = View.GONE
                } else {
                    constant.b_time = ItemsViewModel.getDate().toString().trim()
                    holder.subtask_time.visibility = View.VISIBLE


                    if (current_date > (value_date)) {
                        holder.alert_icon.visibility = View.VISIBLE
                    } else {
                        holder.alert_icon.visibility = View.GONE
                    }



                    if (constant.b_time.equals(s_yesterday)) {
                        holder.subtask_time.setText(
                            "Yesterday," + ItemsViewModel.getDate().toString().trim()
                        )
                    } else if (constant.b_time.equals(s_today)) {
                        holder.subtask_time.setText(
                            "Today," + ItemsViewModel.getDate().toString().trim()
                        )
                    } else if (constant.b_time.equals(s_tomorrow)) {
                        holder.subtask_time.setText(
                            "Tomorrow," + ItemsViewModel.getDate().toString().trim()
                        )
                    } else {
                        holder.subtask_time.setText(ItemsViewModel.getDate().toString().trim())
                    }

                }


            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////// end

        holder.subtask_txt.setText(ItemsViewModel.getTitle())
        //+"\n"+ ItemsViewModel.getList_name() +" - "+ItemsViewModel.getEnd_time()

        if (color.equals("shade_1")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_1_txt
                )
            )
        }
        if (color.equals("shade_2")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_2_txt
                )
            )
        }
        if (color.equals("shade_3")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_3_txt
                )
            )
        }
        if (color.equals("shade_4")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_4_txt
                )
            )
        }
        if (color.equals("shade_5")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_5_txt
                )
            )
        }
        if (color.equals("shade_6")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_6_txt
                )
            )
        }
        if (color.equals("shade_7")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_7_txt
                )
            )
        }
        if (color.equals("shade_8")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_8_txt
                )
            )
        }
        if (color.equals("shade_9")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_9_txt
                )
            )
        }
        if (color.equals("shade_10")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_10_txt
                )
            )
        }
        if (color.equals("shade_11")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_11_txt
                )
            )
        }
        if (color.equals("shade_12")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_12_txt
                )
            )
        }
        if (color.equals("shade_13")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_13_txt
                )
            )
        }
        if (color.equals("shade_14")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_14_txt
                )
            )
        }
        if (color.equals("shade_15")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_15_txt
                )
            )
        }
        if (color.equals("shade_16")) {
            holder.subtask_txt.setBackground(
                mContext!!.resources.getDrawable(
                    R.drawable.shade_16_txt
                )
            )
        }




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
        val alert_icon: ImageView = itemView.findViewById(R.id.alert_icon)


    }
}