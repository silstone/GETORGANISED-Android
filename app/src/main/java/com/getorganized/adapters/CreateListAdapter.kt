package com.getorganized.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.activity.Createlist
import com.getorganized.model_classes.TaskList

class CreateListAdapter(): RecyclerView.Adapter<CreateListAdapter.ViewHolder>() {

    lateinit var mList: List<TaskList>
    lateinit var createlist: Createlist
    private var mContext: Context? = null

    constructor(list: List<TaskList>, context: Context) : this() {
        mList = list
        this.mContext = context;
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.create_list_adapter, parent, false)
        createlist = Createlist()

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        //  holder.three_dots_img.setImageResource(ItemsViewModel.image)
        holder.card_text.text = ItemsViewModel.getname()

       var color= ""
        val color_name = ItemsViewModel.getcolorname()
        Log.e("color" , color_name.toString())
       if (color_name.equals("shade_0")){        holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_0)!!)  }
       else if (color_name.equals("shade_1")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_1)!!)  }
       else if (color_name.equals("shade_2")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_2)!!)  }
       else if (color_name.equals("shade_3")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_3)!!)  }
       else if (color_name.equals("shade_4")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_4)!!)  }
       else if (color_name.equals("shade_5")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_5)!!)  }
       else if (color_name.equals("shade_6")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_6)!!)  }
       else if (color_name.equals("shade_7")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_7)!!)  }
       else if (color_name.equals("shade_8")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_8)!!)  }
       else if (color_name.equals("shade_9")){   holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_9)!!)  }
       else if (color_name.equals("shade_10")){  holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_10)!!) }
       else if (color_name.equals("shade_11")){  holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_11)!!) }
       else if (color_name.equals("shade_12")){  holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_12)!!) }
       else if (color_name.equals("shade_13")){  holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_13)!!) }
       else if (color_name.equals("shade_14")){  holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_14)!!) }
       else if (color_name.equals("shade_15")){  holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_15)!!) }
       else if (color_name.equals("shade_16")){  holder.cardview.setCardBackgroundColor( mContext?.resources?.getColor(R.color.shade_16)!!) }


      /*  holder.txt_country.setOnClickListener {
            if (mContext is Createlist) {
                (mContext as Createlist).close(holder.txt_country.text.toString())
            }

        }*/

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val cardview: CardView = itemView.findViewById(R.id.cardview)
        val card_text: TextView = itemView.findViewById(R.id.card_text)

    }
}