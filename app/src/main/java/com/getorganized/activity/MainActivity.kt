package com.getorganized.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.adapters.DashboardListAdapter
import com.getorganized.adapters.TaskListAdapter
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    lateinit var first_layout: LinearLayout
    lateinit var second_layout: LinearLayout
    lateinit var third_layout: LinearLayout
    lateinit var swipe_layout: RelativeLayout
    lateinit var add_list: LinearLayout


   // var swiped: Boolean = false

    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)

        val completed_btn = findViewById(R.id.completed_btn) as ImageView
        val list_recycler_view = findViewById(R.id.list_recycler_view) as RecyclerView
        val new_task = findViewById(R.id.new_task) as TextView
        val swipe_txt = findViewById(R.id.swipe_txt) as TextView
        val get_started = findViewById(R.id.get_started) as TextView
        val add_list_txt = findViewById(R.id.add_list_txt) as TextView
        val schedule_txt = findViewById(R.id.schedule_txt) as TextView
        val delete_done_layout = findViewById(R.id.delete_done_layout) as RelativeLayout
        val task_list_layout = findViewById(R.id.task_list_layout) as RelativeLayout

        val cross_btn = findViewById(R.id.cross_btn) as ImageView
        val cross_btn2 = findViewById(R.id.cross_btn2) as ImageView
        val task_recycler_view = findViewById(R.id.task_recycler_view) as RecyclerView


        val delete_task = findViewById(R.id.delete_task) as LinearLayout
        val mark_done = findViewById(R.id.mark_done) as LinearLayout


        first_layout = findViewById(R.id.first_layout) as LinearLayout
        second_layout = findViewById(R.id.second_layout) as LinearLayout
        third_layout = findViewById(R.id.third_layout) as LinearLayout
        add_list = findViewById(R.id.add_list) as LinearLayout
        swipe_layout = findViewById(R.id.swipe_layout) as RelativeLayout


        db = FirebaseFirestore.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        val numbersList = intent.getSerializableExtra("mylist") as ArrayList<String>?

        val intent = intent
        val hashMap = intent.getSerializableExtra("mylist") as HashMap<Int, String>?

        list_recycler_view.layoutManager = LinearLayoutManager(this)
        list_recycler_view.adapter = hashMap?.let { DashboardListAdapter(it, this) }

        task_recycler_view.layoutManager = LinearLayoutManager(this)
        task_recycler_view.adapter = hashMap?.let { TaskListAdapter(it, this) }


        /*   list_recycler_view.setOnClickListener {

               first_layout.visibility = View.GONE
               third_layout.visibility = View.VISIBLE
               second_layout.visibility = View.GONE
               swipe_layout.visibility = View.GONE
           }*/

        new_task.setOnClickListener {

            // Check if we're running on Android 5.0 or higher
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent(this, ScheduleTask::class.java)
                intent.putExtra("mylist", hashMap);
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else {
                val intent = Intent(this, ScheduleTask::class.java)
                intent.putExtra("mylist", hashMap);
                startActivity(intent)
            }
        }

        swipe_layout.setOnClickListener {
            // Check if we're running on Android 5.0 or higher
          //  swiped = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent(this, ScheduleTask::class.java)
                intent.putExtra("mylist", hashMap);
                startActivity(intent)
               // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else {
                val intent = Intent(this, ScheduleTask::class.java)
                intent.putExtra("mylist", hashMap);
                startActivity(intent)
            }
        }


        completed_btn.setOnClickListener {
            val intent = Intent(this, CompletedList_Activity::class.java)
            startActivity(intent)

        }

        get_started.setOnClickListener {
            delete_done_layout.visibility = View.VISIBLE

        }

        add_list.setOnClickListener {
            delete_done_layout.visibility = View.GONE
            task_list_layout.visibility = View.VISIBLE
        }
        cross_btn.setOnClickListener {
            task_list_layout.visibility = View.GONE
            delete_done_layout.visibility = View.VISIBLE
            add_list_txt.setText("Work")
            schedule_txt.setText("Nov 4, 2020, 12:30pm -12:45pm")
        }
        cross_btn2.setOnClickListener {
            delete_done_layout.visibility = View.GONE
        }

        delete_task.setOnClickListener {
            delete_done_layout.visibility = View.GONE
        }

        mark_done.setOnClickListener {
            delete_done_layout.visibility = View.GONE
        }

    }

    override fun onResume() {
        super.onResume()

       /* if (swiped) {
            Log.e("swiped true", swiped.toString())
            //swiped = false
            first_layout.visibility = View.GONE
            second_layout.visibility = View.VISIBLE
            swipe_layout.visibility = View.GONE

        } else {
            Log.e("swiped false", swiped.toString())
            first_layout.visibility = View.VISIBLE
            second_layout.visibility = View.GONE
            swipe_layout.visibility = View.GONE
        }*/


    }

    /*  override fun onRestart() {
          super.onRestart()
          second_layout.visibility = View.VISIBLE
      }*/


}