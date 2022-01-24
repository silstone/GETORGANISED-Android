package com.getorganized.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.adapters.DateListAdapter
import com.getorganized.adapters.TaskListAdapter


class ScheduleTask : AppCompatActivity() {

    var s_txt: String = ""
    var mainActivity = MainActivity()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_task)

        val new_task = findViewById(R.id.new_task) as TextView
        val swipe_txt = findViewById(R.id.swipe_txt) as TextView
        val save = findViewById(R.id.save) as TextView
        val cancle = findViewById(R.id.cancle) as TextView
        val new_list_txt = findViewById(R.id.new_list_txt) as TextView
        val add_task_btn = findViewById(R.id.add_task_btn) as ImageView
        val start_time = findViewById(R.id.start_time) as TextView
        val end_time = findViewById(R.id.end_time) as TextView

        val start_txt = findViewById(R.id.start_txt) as TextView
        val end_txt = findViewById(R.id.end_txt) as TextView

        val add_list_txt = findViewById(R.id.add_list_txt) as TextView
        val schedule_txt = findViewById(R.id.schedule_txt) as TextView

        val date_recycler_view = findViewById(R.id.date_recycler_view) as RecyclerView

        val start_clock_img = findViewById(R.id.start_clock_img) as ImageView
        val end_clock_img = findViewById(R.id.end_clock_img) as ImageView
        val menu_btn = findViewById(R.id.menu_btn) as ImageView

        val scroll_view = findViewById(R.id.scroll_view) as ScrollView



        val cross_btn = findViewById(R.id.cross_btn) as ImageView
        val cross_btn2 = findViewById(R.id.cross_btn2) as ImageView
        val task_recycler_view = findViewById(R.id.task_recycler_view) as RecyclerView
        val recycler_list = findViewById(R.id.recycler_list) as LinearLayout

        val date_list_layout = findViewById(R.id.date_list_layout) as LinearLayout
        val top_calender_layout = findViewById(R.id.top_calender_layout) as LinearLayout


        val create_task = findViewById(R.id.create_task) as RelativeLayout
        val task_list_layout = findViewById(R.id.task_list_layout) as RelativeLayout
        val schedule_time_layout = findViewById(R.id.schedule_time_layout) as RelativeLayout
        val date_picker_layout = findViewById(R.id.date_picker_layout) as RelativeLayout
        val start_time_layout = findViewById(R.id.start_time_layout) as RelativeLayout
        val end_time_layout = findViewById(R.id.end_time_layout) as RelativeLayout
        val swipe_layout = findViewById(R.id.swipe_layout) as RelativeLayout
        val delete_done_layout = findViewById(R.id.delete_done_layout) as RelativeLayout
        val first_layout = findViewById(R.id.first_layout) as RelativeLayout
        val second_layout = findViewById(R.id.second_layout) as LinearLayout
        val delete_task = findViewById(R.id.delete_task) as LinearLayout
        val mark_done = findViewById(R.id.mark_done) as LinearLayout


        val cardview = findViewById(R.id.cardview) as CardView
        val time_seprator = findViewById(R.id.time_seprator) as ImageView


        val task_name = findViewById(R.id.task_name) as AppCompatEditText
        val confirm_layout = findViewById(R.id.confirm_layout) as LinearLayout

        val schedule_time = findViewById(R.id.schedule_time) as LinearLayout
        val add_list_layout = findViewById(R.id.add_list_layout) as LinearLayout


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val intent = intent
        val hashMap = intent.getSerializableExtra("mylist") as HashMap<Int, String>?

        task_recycler_view.layoutManager = LinearLayoutManager(this)
        task_recycler_view.adapter = hashMap?.let { TaskListAdapter(it, this) }

        val hashMap_date: java.util.HashMap<Int, String> = java.util.HashMap<Int, String>()
        val names = listOf("1", "2", "3", "4", "5", "6", "7")

        for (i in names) {
            hashMap_date.put(0, "10")
            hashMap_date.put(1, "9")
            hashMap_date.put(2, "8")
            hashMap_date.put(3, "7")
            hashMap_date.put(4, "6")
            hashMap_date.put(5, "5")
            hashMap_date.put(6, "4")
            hashMap_date.put(7, "3")
            hashMap_date.put(8, "2")
            hashMap_date.put(9, "1")
        }

        date_recycler_view.layoutManager = LinearLayoutManager(this)
        date_recycler_view.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                true
            )
        )

        date_recycler_view.adapter = hashMap_date?.let { DateListAdapter(it, this) }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scroll_view.setOnScrollChangeListener(View.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val bottom: Int = scroll_view.getChildAt(scroll_view.getChildCount() - 1)
                    .getHeight() - scroll_view.getHeight() - scrollY
                if (scrollY == 0) {
                    //top detected

                    top_calender_layout.visibility = View.VISIBLE
                    date_list_layout.visibility = View.GONE
                }
                if (bottom == 0) {
                    //bottom detected
                    top_calender_layout.visibility = View.GONE
                    date_list_layout.visibility = View.VISIBLE
                }
            })
        }

        
        val myToast = Toast.makeText(
            applicationContext,
            "Calendar view is temporary for now, will change when functionality occurs",
            Toast.LENGTH_SHORT
        ).show()


        menu_btn.setOnClickListener {
            // Check if we're running on Android 5.0 or higher
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent(this, SettingScreen::class.java)
                startActivity(intent)
               // overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right)
            } else {
                val intent = Intent(this, SettingScreen::class.java)
                startActivity(intent)
            }
        }

        new_task.setOnClickListener {
            create_task.visibility = View.VISIBLE
            swipe_layout.visibility = View.GONE
            showSoftKeyboard(task_name);
        }

        // set on-click listener
        add_task_btn.setOnClickListener {

            create_task.visibility = View.VISIBLE
            swipe_layout.visibility = View.GONE
            showSoftKeyboard(task_name);
        }

        cross_btn.setOnClickListener {
            task_list_layout.visibility = GONE
            create_task.visibility = VISIBLE
            showSoftKeyboard(task_name)
        }
        cross_btn2.setOnClickListener {
            delete_done_layout.visibility = GONE

        }
        mark_done.setOnClickListener {
            delete_done_layout.visibility = GONE

        }
        delete_task.setOnClickListener {
            delete_done_layout.visibility = GONE
        }



        cancle.setOnClickListener {
            task_list_layout.visibility = GONE
            create_task.visibility = VISIBLE
            schedule_time_layout.visibility = GONE
            date_picker_layout.visibility = GONE
            showSoftKeyboard(task_name)
            recycler_list.visibility = VISIBLE
            date_picker_layout.visibility = GONE
        }

        cardview.setOnClickListener {
            delete_done_layout.visibility = VISIBLE
        }
        


        save.setOnClickListener {
            task_list_layout.visibility = GONE
            schedule_time_layout.visibility = GONE
            date_picker_layout.visibility = GONE
            create_task.visibility = VISIBLE
            showSoftKeyboard(task_name)
            recycler_list.visibility = VISIBLE
            date_picker_layout.visibility = GONE

            add_list_txt.setText("work")
            schedule_txt.setText("Nov 11, 2020, 11:00am- 11:15pm")
        }


        start_time_layout.setOnClickListener {
            recycler_list.visibility = GONE
            date_picker_layout.visibility = VISIBLE
            start_time.setTextColor(resources.getColor(R.color.white))
            start_txt.setTextColor(resources.getColor(R.color.white))
            start_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon_white))
            start_time_layout.setBackground(resources.getDrawable(R.drawable.time_selected_background))
            time_seprator.visibility = GONE

            end_time.setTextColor(resources.getColor(R.color.black))
            end_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon))
            end_time_layout.setBackgroundColor(resources.getColor(R.color.white))

        }

      /*  end_time_layout.setOnClickListener {
            recycler_list.visibility = GONE
            date_picker_layout.visibility = VISIBLE
            end_time.setTextColor(resources.getColor(R.color.white))
            end_txt.setTextColor(resources.getColor(R.color.white))
            end_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon_white))
            end_time_layout.setBackgroundColor(resources.getColor(R.color.shade_14))
            time_seprator.visibility = GONE

            start_time.setTextColor(resources.getColor(R.color.black))
            start_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon))
            start_time_layout.setBackgroundColor(resources.getColor(R.color.white))

        }*/

        add_list_layout.setOnClickListener {
            task_list_layout.visibility = VISIBLE
            create_task.visibility = GONE
            swipe_layout.visibility = View.VISIBLE
            hideSoftKeyboard(task_name)
        }
        schedule_time.setOnClickListener {
            schedule_time_layout.visibility = VISIBLE
            create_task.visibility = GONE
            task_list_layout.visibility = GONE
            hideSoftKeyboard(task_name)
        }

        confirm_layout.setOnClickListener {
            create_task.visibility = View.GONE
            hideSoftKeyboard(task_name)
            first_layout.visibility = View.GONE
            second_layout.visibility = View.VISIBLE
            s_txt = task_name.text.toString();
            task_name.setText("")
            swipe_layout.visibility = View.VISIBLE

        }

        task_name.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    // val myToast = Toast.makeText(applicationContext,""+count,Toast.LENGTH_SHORT).show()
                    confirm_layout.setBackgroundColor(resources.getColor(R.color.button_color))
                    confirm_layout.isClickable = true
                } else {
                    confirm_layout.setBackgroundColor(resources.getColor(R.color.txt_gray))
                    confirm_layout.isClickable = false
                }
            }
        });

        task_name.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Your action on done
                create_task.visibility = View.GONE
                hideSoftKeyboard(task_name)
                s_txt = task_name.text.toString();
                task_name.setText("")
                swipe_layout.visibility = View.VISIBLE
                /* s_txt = list_name.text.toString();
                 if (s_txt.length > 0) {
                     cardview.visibility = View.VISIBLE
                     next_button.visibility = View.VISIBLE
                 } else {
                     cardview.visibility = View.GONE
                     next_button.visibility = View.GONE
                 }
                 card_text.setText(s_txt)
                 list_name.setText("")*/

                true
            } else false
        })

        swipe_txt.setOnClickListener {
            // Check if we're running on Android 5.0 or higher
          //  mainActivity.swiped = true
          //  Log.e("swiped schedule", mainActivity.swiped.toString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               // overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            } else {
               // overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
        }

    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


}
