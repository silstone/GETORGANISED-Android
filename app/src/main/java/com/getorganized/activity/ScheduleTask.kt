package com.getorganized.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Log.println
import android.view.GestureDetector
import android.view.MotionEvent
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
import androidx.core.text.trimmedLength
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.adapters.*
import com.getorganized.model_classes.SubTask
import com.getorganized.model_classes.TaskList
import com.getorganized.utils.Constant
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.DriverManager.println
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ScheduleTask : AppCompatActivity() {

    var s_txt: String = ""
    var delete_node_id: String = ""

    // var mainActivity = MainActivity()
    var list_color = "shade_0"
    var mLayoutManager2: RecyclerView.LayoutManager? = null
    var mLayoutManager: RecyclerView.LayoutManager? = null
    var mLayoutManager3: RecyclerView.LayoutManager? = null
    var mLayoutManager4: RecyclerView.LayoutManager? = null
    lateinit var create_task: RelativeLayout
    lateinit var task_list_layout: RelativeLayout
    lateinit var first_layout: RelativeLayout
    lateinit var delete_done_layout: RelativeLayout
    lateinit var swipe_layout: RelativeLayout

    lateinit var second_layout: LinearLayout

    var mytaskList: ArrayList<TaskList> = ArrayList<TaskList>()
    var subtasklist: ArrayList<SubTask> = ArrayList<SubTask>()
    var subtasklist_main: ArrayList<SubTask> = ArrayList<SubTask>()
    var addlist_adapter: AddListAdapter? = null
    var sub_listadapter: SubListAdapter? = null
    var date_listadapter: DateListAdapter? = null

    var schedule_sub_listadapter: Schedule_SubListAdapter? = null

    val value_list: MutableList<String> = ArrayList()

    lateinit var task_recycler_view: RecyclerView
    lateinit var date_recycler_view: RecyclerView
    lateinit var sublist_recycler: RecyclerView
    lateinit var schedule_sublist_recycler: RecyclerView

    lateinit var calender_view1: com.applandeo.materialcalendarview.CalendarView
    lateinit var calender_view2: com.applandeo.materialcalendarview.CalendarView

    var selected_date = ""
    var calendar_time: Date = Calendar.getInstance().time

    var singleBuilder: SingleDateAndTimePickerDialog.Builder =
        SingleDateAndTimePickerDialog.Builder(
            this
        )
    val constant = Constant()
    var firestore = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth? = null
    private lateinit var progressDialog: ProgressDialog
    var user_id = ""


    lateinit var task_name: AppCompatEditText

    lateinit var add_list_txt: TextView
    lateinit var schedule_txt: TextView
    lateinit var ok: TextView
    lateinit var task_detail: TextView

    lateinit var cross_task: ImageView

    lateinit var start_time: TextView
    lateinit var end_time: TextView

    var start_t = ""
    var end_t = ""


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
        start_time = findViewById(R.id.start_time) as TextView
        end_time = findViewById(R.id.end_time) as TextView
        task_detail = findViewById(R.id.task_detail) as TextView
        var start_txt = findViewById(R.id.start_txt) as TextView
        var end_txt = findViewById(R.id.end_txt) as TextView
        var txt_month_name = findViewById(R.id.txt_month_name) as TextView

        add_list_txt = findViewById(R.id.add_list_txt) as TextView
        schedule_txt = findViewById(R.id.schedule_txt) as TextView
        ok = findViewById(R.id.ok) as TextView

        cross_task = findViewById(R.id.cross_task) as ImageView
        date_recycler_view = findViewById(R.id.date_recycler_view) as RecyclerView

        val start_clock_img = findViewById(R.id.start_clock_img) as ImageView
        val end_clock_img = findViewById(R.id.end_clock_img) as ImageView
        val menu_btn = findViewById(R.id.menu_btn) as ImageView

        val scroll_view = findViewById(R.id.scroll_view) as ScrollView


        val cross_btn = findViewById(R.id.cross_btn) as ImageView
        val cross_btn2 = findViewById(R.id.cross_btn2) as ImageView
        task_recycler_view = findViewById(R.id.task_recycler_view) as RecyclerView
        sublist_recycler = findViewById(R.id.sublist_recycler) as RecyclerView
        schedule_sublist_recycler = findViewById(R.id.schedule_sublist_recycler) as RecyclerView

        calender_view1 =
            findViewById(R.id.calender_view1) as com.applandeo.materialcalendarview.CalendarView
        calender_view2 =
            findViewById(R.id.calender_view2) as com.applandeo.materialcalendarview.CalendarView

        val sublist_layout = findViewById(R.id.sublist_layout) as LinearLayout

        val date_list_layout = findViewById(R.id.date_list_layout) as LinearLayout
        val top_calender_layout = findViewById(R.id.top_calender_layout) as LinearLayout


        create_task = findViewById(R.id.create_task) as RelativeLayout
        task_list_layout = findViewById(R.id.task_list_layout) as RelativeLayout
        val schedule_time_layout = findViewById(R.id.schedule_time_layout) as RelativeLayout
        val date_picker_layout = findViewById(R.id.date_picker_layout) as RelativeLayout
        val start_time_layout = findViewById(R.id.start_time_layout) as RelativeLayout
        val end_time_layout = findViewById(R.id.end_time_layout) as RelativeLayout
        swipe_layout = findViewById(R.id.swipe_layout) as RelativeLayout
        delete_done_layout = findViewById(R.id.delete_done_layout) as RelativeLayout
        first_layout = findViewById(R.id.first_layout) as RelativeLayout
        second_layout = findViewById(R.id.second_layout) as LinearLayout
        val delete_task = findViewById(R.id.delete_task) as LinearLayout
        val mark_done = findViewById(R.id.mark_done) as LinearLayout


        val time_seprator = findViewById(R.id.time_seprator) as ImageView


        task_name = findViewById(R.id.task_name) as AppCompatEditText
        val confirm_layout = findViewById(R.id.confirm_layout) as LinearLayout

        val schedule_time = findViewById(R.id.schedule_time) as LinearLayout
        val add_list_layout = findViewById(R.id.add_list_layout) as LinearLayout


        firestore = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        user_id = mAuth!!.uid.toString()
        //  ref = firestore.collection(constant.USERS).document(user_id).collection(constant.TASKS).get()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        progressDialog = ProgressDialog(this@ScheduleTask)

        mLayoutManager2 = LinearLayoutManager(this)
        task_recycler_view?.setLayoutManager(mLayoutManager2)
        addlist_adapter = AddListAdapter(mytaskList, this)


        mLayoutManager = LinearLayoutManager(this)
        sublist_recycler?.setLayoutManager(mLayoutManager)
        sub_listadapter = SubListAdapter(subtasklist, this)


        mLayoutManager3 = LinearLayoutManager(this)
        schedule_sublist_recycler?.setLayoutManager(mLayoutManager3)
        schedule_sub_listadapter = Schedule_SubListAdapter(subtasklist, this)


        mLayoutManager4 = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        date_recycler_view?.setLayoutManager(mLayoutManager4)
        date_listadapter = DateListAdapter(value_list, this)


        /*val hashMap_date: java.util.HashMap<Int, String> = java.util.HashMap<Int, String>()
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
        }*/


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


        /*val myToast = Toast.makeText(
            applicationContext,
            "Calendar view is temporary for now, will change when functionality occurs",
            Toast.LENGTH_SHORT
        ).show()*/


        calender_view1.setOnTouchListener(object :
            ScheduleTask.OnSwipeTouchListener(this@ScheduleTask) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                /*Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()*/

            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                /* Toast.makeText(
                     this@MainActivity,
                     "Swipe Right gesture detected",
                     Toast.LENGTH_SHORT
                 ).show()*/
                /* val intent = Intent(this@ScheduleTask, MainActivity::class.java)
                 startActivity(intent)*/
                finish()
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                /* Toast.makeText(this@MainActivity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                     .show()*/
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                /*Toast.makeText(this@MainActivity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }
        })

        first_layout.setOnTouchListener(object :
            ScheduleTask.OnSwipeTouchListener(this@ScheduleTask) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                /*Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()*/

            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                /* Toast.makeText(
                     this@MainActivity,
                     "Swipe Right gesture detected",
                     Toast.LENGTH_SHORT
                 ).show()*/
                finish()
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                /* Toast.makeText(this@MainActivity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                     .show()*/
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                /*Toast.makeText(this@MainActivity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }
        })

        schedule_sublist_recycler.setOnTouchListener(object :
            ScheduleTask.OnSwipeTouchListener(this@ScheduleTask) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                /*Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()*/

            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                /* Toast.makeText(
                     this@MainActivity,
                     "Swipe Right gesture detected",
                     Toast.LENGTH_SHORT
                 ).show()*/
                finish()
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                /* Toast.makeText(this@MainActivity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                     .show()*/
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                /*Toast.makeText(this@MainActivity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }
        })


        scroll_view.setOnTouchListener(object :
            ScheduleTask.OnSwipeTouchListener(this@ScheduleTask) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                /*Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()*/

            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                /* Toast.makeText(
                     this@MainActivity,
                     "Swipe Right gesture detected",
                     Toast.LENGTH_SHORT
                 ).show()*/
                finish()
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                /* Toast.makeText(this@MainActivity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                     .show()*/
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                /*Toast.makeText(this@MainActivity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }
        })


        cross_task.setOnClickListener {
            task_list_layout.visibility = GONE
            create_task.visibility = GONE
            hideSoftKeyboard(task_name)
        }




        ok.setOnClickListener {
            sublist_layout.visibility = VISIBLE
            sub_listadapter!!.notifyDataSetChanged()
            date_picker_layout.visibility = GONE
        }


        new_list_txt.setOnClickListener {

            val intent = Intent(this, Createlist::class.java)
            startActivity(intent)

        }

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
            hideSoftKeyboard(task_name)
        }
        cross_btn2.setOnClickListener {
            delete_done_layout.visibility = GONE
            hideSoftKeyboard(task_name)

        }


        cancle.setOnClickListener {
            task_list_layout.visibility = GONE
            create_task.visibility = VISIBLE
            schedule_time_layout.visibility = GONE
            date_picker_layout.visibility = GONE
            showSoftKeyboard(task_name)



            start_time.setText("00:00")
            end_time.setText("00:00")
            start_t = ""
            end_t = ""

        }

        delete_task.setOnClickListener {

            progressDialog.setCancelable(false)
            progressDialog.setMessage("Delete in process...")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.show()

            firestore.collection(constant.USERS).document(user_id).collection(constant.SUBTASKS)
                .document(delete_node_id).delete()
                .addOnCompleteListener(
                    OnCompleteListener<Void?> { task ->
                        if (task.isSuccessful) {
                            delete_done_layout.visibility = GONE
                            progressDialog.cancel()
                            delete_node_id = ""
                            getSubTasks()
                            //Log.d(TAG, "User account deleted.");
                        }
                    })
                .addOnFailureListener {
                    progressDialog.cancel()
                }
            delete_done_layout.visibility = View.GONE
        }

        mark_done.setOnClickListener {

            val updatefirestrore =
                firestore.collection(constant.USERS).document(user_id).collection(
                    constant.SUBTASKS
                ).document(delete_node_id)

            updatefirestrore
                .update(constant.task_status, constant.completed)
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }

            Toast.makeText(applicationContext, "Successfully Done", Toast.LENGTH_SHORT).show()
            delete_done_layout.visibility = View.GONE
            getSubTasks()
        }




        calender_view2.setOnDayClickListener { eventDay ->


            calendar_time = eventDay.getCalendar().getTime()

            Log.e("time", calendar_time.toString())//Fri Feb 11 00:00:00 GMT+05:30 2022

            val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
            try {

                val date_select = formatter.parse(calendar_time.toString())
                Log.e("date_select", date_select.toString())

                // val temp = "Thu Dec 17 15:37:43 GMT+05:30 2015"
                // val expiry = formatter.parse(time)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            /*val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
            try {
                val temp = "Thu Dec 17 15:37:43 GMT+05:30 2015"
                val expiry = formatter.parse(temp)
            } catch (e: Exception) {
                e.printStackTrace()
            }*/


            /*  for (calendar in calender_view2.getSelectedDates()) {
         System.out.println(calendar.time.toString())
         Toast.makeText(
             applicationContext,
             calendar.time.toString(),
             Toast.LENGTH_SHORT
         ).show()
     }*/
        }





        save.setOnClickListener {

            if (start_t.equals("") || start_t.equals("00:00")) {
                Toast.makeText(applicationContext, "Please select start time", Toast.LENGTH_SHORT)
                    .show()

            } else {
                if (end_t.equals("") || end_t.equals("00:00")) {
                    Toast.makeText(applicationContext, "Please select end time", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    schedule_txt.setText(selected_date + ", " + start_t + "-" + end_t)

                    task_list_layout.visibility = GONE
                    schedule_time_layout.visibility = GONE
                    date_picker_layout.visibility = GONE
                    create_task.visibility = VISIBLE
                    showSoftKeyboard(task_name)
                    sublist_layout.visibility = GONE

                }
            }


        }


        start_time_layout.setOnClickListener {
            sublist_layout.visibility = GONE
            time_seprator.visibility = GONE
            startDateClicked()   // date_picker_layout.visibility = VISIBLE
            start_time.setTextColor(resources.getColor(R.color.white))
            start_txt.setTextColor(resources.getColor(R.color.white))
            start_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon_white))
            start_time_layout.setBackground(resources.getDrawable(R.drawable.time_selected_background))


            end_time.setTextColor(resources.getColor(R.color.black))
            end_txt.setTextColor(resources.getColor(R.color.black))
            end_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon))
            end_time_layout.setBackgroundColor(resources.getColor(R.color.white))

        }

        end_time_layout.setOnClickListener {
            sublist_layout.visibility = GONE
            endDateClicked()   // date_picker_layout.visibility = VISIBLE
            end_time.setTextColor(resources.getColor(R.color.white))
            end_txt.setTextColor(resources.getColor(R.color.white))
            end_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon_white))
            end_time_layout.setBackgroundColor(resources.getColor(R.color.shade_14))
            time_seprator.visibility = GONE

            start_time.setTextColor(resources.getColor(R.color.black))
            start_txt.setTextColor(resources.getColor(R.color.black))
            start_clock_img.setImageDrawable(resources.getDrawable(R.drawable.clock_icon))
            start_time_layout.setBackgroundColor(resources.getColor(R.color.white))

        }

        add_list_layout.setOnClickListener {
            create_task.visibility = GONE
            swipe_layout.visibility = View.VISIBLE
            task_list_layout.visibility = VISIBLE
            hideSoftKeyboard(task_name)
        }
        schedule_time.setOnClickListener {
            schedule_time_layout.visibility = VISIBLE
            create_task.visibility = GONE
            task_list_layout.visibility = GONE
            hideSoftKeyboard(task_name)
        }

        confirm_layout.setOnClickListener {
            s_txt = task_name.text.toString();
            addSubtask(s_txt, add_list_txt.text.toString().trim())
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




        getTasks()
    }/// oncreate end here

    open class OnSwipeTouchListener(c: Context?) :
        View.OnTouchListener {
        private val gestureDetector: GestureDetector
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            return false  //gestureDetector.onTouchEvent(motionEvent)
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
            private val SWIPE_THRESHOLD: Int = 100
            private val SWIPE_VELOCITY_THRESHOLD: Int = 100
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onSingleTapUp(e: MotionEvent): Boolean {
                onClick()
                return super.onSingleTapUp(e)
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                onDoubleClick()
                return super.onDoubleTap(e)
            }

            override fun onLongPress(e: MotionEvent) {
                onLongClick()
                super.onLongPress(e)
            }

            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(
                                velocityX
                            ) > SWIPE_VELOCITY_THRESHOLD
                        ) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                        }
                    } else {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(
                                velocityY
                            ) > SWIPE_VELOCITY_THRESHOLD
                        ) {
                            if (diffY < 0) {
                                onSwipeUp()
                            } else {
                                onSwipeDown()
                            }
                        }
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                return false
            }
        }

        open fun onSwipeRight() {}
        open fun onSwipeLeft() {}
        open fun onSwipeUp() {}
        open fun onSwipeDown() {}
        private fun onClick() {}
        private fun onDoubleClick() {}
        private fun onLongClick() {}

        init {
            gestureDetector = GestureDetector(c, GestureListener())
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


    fun addSubtask(txt: String, parent_task: String) {

        progressDialog.setCancelable(false)
        progressDialog.setMessage("Creating...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()

        var status = ""
        if (start_t.equals("") || start_t == null) {
            status = constant.Unscheduled
        } else {
            status = constant.Scheduled
        }
        val event_list: HashMap<String, String> = HashMap()
        event_list.put(constant.title, txt)
        event_list.put(constant.list_name, parent_task)
        event_list.put(constant.start_time, start_t)
        event_list.put(constant.end_time, end_t)
        event_list.put(constant.date, selected_date)
        event_list.put(constant.color, list_color)
        event_list.put(constant.task_status, status)

        val subtaskList: SubTask = SubTask()
        subtaskList.setTitle(txt)
        subtaskList.setList_name(parent_task)
        subtaskList.setStart_time("")
        subtaskList.setEnd_time("")
        subtaskList.setDate("")
        subtaskList.setTask_status(constant.Unscheduled)


        firestore.collection(constant.USERS).document(user_id).collection(constant.SUBTASKS)
            .add(event_list)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                subtaskList.setNodeid(documentReference.id)
                progressDialog.cancel()
                hideSoftKeyboard(task_name)
                create_task.visibility = View.GONE

                first_layout.visibility = View.GONE
                second_layout.visibility = View.VISIBLE
                swipe_layout.visibility = View.VISIBLE
                task_name.setText("")

                getSubTasks()
            }
            .addOnFailureListener { e ->
                Log.d("TAG", "Error adding document", e)
                progressDialog.cancel()
                create_task.visibility = View.GONE
            }
    }


    fun showListName(list_name: String, color: String) {
        list_color = color
        Log.e("listname", list_name)
        task_list_layout.visibility = View.GONE
        create_task.visibility = View.VISIBLE
        showSoftKeyboard(task_name);
        add_list_txt.setText(list_name)

    }


    fun showNewTask() {
        create_task.visibility = View.VISIBLE
        showSoftKeyboard(task_name);
    }


    fun getTasks() {//(v: View?)

        progressDialog.setCancelable(false)
        progressDialog.setMessage("Loading...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()



        firestore.collection(constant.USERS).document(user_id).collection(constant.TASKS)
            .get()
            .addOnSuccessListener { result ->

                mytaskList.clear()
                for (document in result) {
                    //  Log.e("success", "${document.id} => ${document.data}")

                    val taskList: TaskList = TaskList()
                    taskList.setname(document.data.get("taskName").toString())
                    taskList.setcolorname(document.data.get("color").toString())
                    mytaskList.add(taskList)

                }


                addlist_adapter = AddListAdapter(mytaskList, this)
                task_recycler_view?.setAdapter(addlist_adapter)
                addlist_adapter?.notifyDataSetChanged()

                //progressDialog.cancel()
                getSubTasks()
            }
            .addOnFailureListener { exception ->
                Log.e("gettask_failure", "Error getting documents: ", exception)
                progressDialog.cancel()
            }
    }

    fun getSubTasks() {//(v: View?)
        subtasklist.clear()
        subtasklist_main.clear()
        firestore.collection(constant.USERS).document(user_id).collection(constant.SUBTASKS)
            .get()
            .addOnSuccessListener { result ->


                value_list.clear()

                for (document in result) {
                    Log.e("success", "${document.id} => ${document.data}")

                    val subtaskList: SubTask = SubTask()
                    subtaskList.setList_name(document.data.get(constant.list_name).toString())
                    subtaskList.setStart_time(document.data.get(constant.start_time).toString())
                    subtaskList.setEnd_time(document.data.get(constant.end_time).toString())
                    subtaskList.setDate(document.data.get(constant.date).toString())
                    subtaskList.setTitle(document.data.get(constant.title).toString())
                    subtaskList.setTask_status(document.data.get(constant.task_status).toString())
                    subtaskList.setNodeid(document.id.toString())
                    subtaskList.setColor(document.data.get(constant.color).toString())

                    subtasklist_main.add(subtaskList)
                    val s_date = document.data.get(constant.date).toString()
                    if (s_date.equals("")) {
                    } else {
                        value_list.add(s_date.toString())
                    }
                    swipe_layout.visibility = View.VISIBLE

                    var name = document.data.get(constant.task_status).toString().trim()
                    if (name.equals(constant.completed)) {

                    } else if (document.data.get(constant.list_name).toString()
                            .equals(constant.inbox)
                    ) {

                    } else {
                        subtasklist.add(subtaskList)

                    }

                }

                if (subtasklist.size > 0) {

                    if (value_list.size > 0) {
                        Log.e("value_list", value_list.toString())
                        Collections.sort(value_list, StringDateComparator())
                    }

                    if (subtasklist_main.size > 0) {
                        Collections.sort(subtasklist_main, MainActivity.sortItems())
                    }
                    second_layout.visibility = View.VISIBLE
                    first_layout.visibility = View.GONE

                    sub_listadapter = SubListAdapter(subtasklist, this)
                    sublist_recycler?.setAdapter(sub_listadapter)
                    sub_listadapter?.notifyDataSetChanged()

                    schedule_sub_listadapter = Schedule_SubListAdapter(subtasklist_main, this)
                    schedule_sublist_recycler?.setAdapter(schedule_sub_listadapter)
                    schedule_sub_listadapter?.notifyDataSetChanged()




                    date_listadapter = DateListAdapter(value_list, this)
                    date_recycler_view?.setAdapter(date_listadapter)
                    date_listadapter?.notifyDataSetChanged()

                    progressDialog.cancel()

                } else {
                    second_layout.visibility = View.GONE
                    first_layout.visibility = View.VISIBLE
                    progressDialog.cancel()
                }


            }
            .addOnFailureListener { exception ->
                Log.e("failure", "Error getting documents: ", exception)
                progressDialog.cancel()
            }
    }


    override fun onPause() {
        super.onPause()
        if (singleBuilder != null) singleBuilder!!.dismiss()

    }


    fun startDateClicked() {
        val calendar = Calendar.getInstance()
        // val defaultDate = calendar.time
        var newdate: Date
        if (calendar_time.equals("")) {
            newdate = Calendar.getInstance().time
        } else {
            newdate = calendar_time
        }

        Log.e("timeZone", calendar.timeZone.toString())

        singleBuilder = SingleDateAndTimePickerDialog.Builder(this)
            .defaultDate(newdate)
            .setTimeZone(TimeZone.getDefault())
            .bottomSheet()
            .curved()
            //.titleTextColor(Color.GREEN)
            //.backgroundColor(Color.BLACK)
            //.mainColor(Color.GREEN)
            .displayHours(true)
            .displayMinutes(true)
            .displayDays(true)
            .displayAmPm(true)
            .displayListener(object : SingleDateAndTimePickerDialog.DisplayListener {
                override fun onDisplayed(picker: SingleDateAndTimePicker) {
                    Log.e("TAG", "Dialog displayed")
                }

                fun onClosed(picker: SingleDateAndTimePicker?) {
                    Log.e("TAG2", "Dialog closed")
                }
            })
            .title("")
            .listener { date ->
                Log.e("date", date.toString())
                selected_date = ""
                start_t = ""
                selected_date = getFormattedDate(date).toString()

                start_t = getTime(date).toString()
                start_time.setText(start_t)

                calender_view2.setDate(date)
                Log.e("selected_date", selected_date.toString())
            }
        this.singleBuilder.display()
    }


    fun endDateClicked() {
        val calendar = Calendar.getInstance()
        val defaultDate = calendar.time
        singleBuilder = SingleDateAndTimePickerDialog.Builder(this)
            .setTimeZone(TimeZone.getDefault())
            .bottomSheet()
            .curved()
            //.titleTextColor(Color.GREEN)
            //.backgroundColor(Color.BLACK)
            //.mainColor(Color.GREEN)
            .displayHours(true)
            .displayMinutes(true)
            .displayDays(false)
            .displayAmPm(true)
            .displayListener(object : SingleDateAndTimePickerDialog.DisplayListener {
                override fun onDisplayed(picker: SingleDateAndTimePicker) {
                    Log.e("TAG", "Dialog displayed")
                }

                fun onClosed(picker: SingleDateAndTimePicker?) {
                    Log.e("TAG2", "Dialog closed")
                }
            })
            .title("")
            .listener { date ->
                Log.e("date", date.toString())
                end_t = ""
                end_t = getendTime(date).toString()
                end_time.setText(end_t)
                Log.e("selected_date", selected_date.toString())

            }
        this.singleBuilder.display()
    }


    private fun getFormattedDate(timeZone: Date): String? {
        val df = SimpleDateFormat("MMM dd, yyyy")
        return df.format(timeZone)
    }

    private fun getTime(timeZone: Date): String? {
        val df = SimpleDateFormat("hh:mm:aa")
        val ef = df.format(timeZone)
        val str = ef.replace("am", "AM").replace("pm", "PM")
        return str
    }

    private fun getendTime(timeZone: Date): String? {
        val df = SimpleDateFormat("hh:mm:aa")
        val ef = df.format(timeZone)
        val str = ef.replace("am", "AM").replace("pm", "PM")
        return str
    }

    fun Convert24to12(time: String?): String? {
        var convertedTime = ""
        try {
            val displayFormat = SimpleDateFormat("hh:mm a")
            val parseFormat = SimpleDateFormat("HH:mm:ss")
            val date = parseFormat.parse(time)
            convertedTime = displayFormat.format(date)
            println("convertedTime : $convertedTime")
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return convertedTime
        //Output will be 10:23 PM
    }

    fun showDetail(node_id: String, txt: String, color: String) {
        delete_node_id = node_id
        task_detail.setText(txt)
        delete_done_layout.visibility = View.VISIBLE
    }


    class StringDateComparator : Comparator<String?> {


        var dateFormat = SimpleDateFormat("MMM dd, yyyy")  //Mar 03, 2024
        override fun compare(lhs: String?, rhs: String?): Int {
            return dateFormat.parse(lhs).compareTo(dateFormat.parse(rhs))
        }
    }

/*Collections.sort(datestring, new Comparator<String>() {
   DateFormat f = new SimpleDateFormat("dd/MM/yyyy '@'hh:mm a");
   @Override
   public int compare(String o1, String o2) {
     try {
       return f.parse(o1).compareTo(f.parse(o2));
     } catch (ParseException e) {
       throw new IllegalArgumentException(e);
     }
   }
 });*/
    
    internal class shorting : Comparator<SubTask?> {
        override fun compare(a: SubTask?, b: SubTask?): Int {
            return a?.getDate().toString().compareTo(b?.getDate().toString())
        }
    }





}
