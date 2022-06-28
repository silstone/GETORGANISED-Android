package com.getorganized.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.adapters.*
import com.getorganized.model_classes.SubTask
import com.getorganized.model_classes.TaskList
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Math.abs
import java.sql.DriverManager.println
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {
    lateinit var ref: Task<QuerySnapshot>
    lateinit var first_layout: LinearLayout
    lateinit var second_layout: LinearLayout
    lateinit var third_layout: LinearLayout

    lateinit var swipe_layout: RelativeLayout
    lateinit var scroll_1: ScrollView
    lateinit var scroll_2: ScrollView

    // lateinit var swipe: RelativeLayout
    // lateinit var add_list_new: LinearLayout
    lateinit var add_list: LinearLayout

    var delete_node_id = ""

    private lateinit var progressDialog: ProgressDialog
    var user_id = ""
    var dashboardListAdapter: DashboardListAdapter? = null
    var mLayoutManager: RecyclerView.LayoutManager? = null
    var mLayoutManager3: RecyclerView.LayoutManager? = null
    var mLayoutManager4: RecyclerView.LayoutManager? = null


    lateinit var task_recycler_view: RecyclerView
    lateinit var list_recycler_view: RecyclerView
    lateinit var inbox_recycler: RecyclerView
    lateinit var subtask_recycler: RecyclerView


    lateinit var delete_done_layout: RelativeLayout
    lateinit var task_list_layout: RelativeLayout
    lateinit var add_list_txt: TextView
    lateinit var task_detail: TextView

    // lateinit var new_addlist: TextView
    // lateinit var new_schedule: TextView

    lateinit var schedule_txt: TextView

    var inbox_adapter = Inbox_Adapter()
    var task_adapter: TaskListAdapter? = null
    var subtsk_adapter: Subtask_Adapter? = null


    var myList: ArrayList<TaskList> = ArrayList<TaskList>()
    var sublist: ArrayList<SubTask> = ArrayList<SubTask>()
    var inboxlist: ArrayList<SubTask> = ArrayList<SubTask>()
    var newsublist: ArrayList<SubTask> = ArrayList<SubTask>()
    var completedlist: ArrayList<SubTask> = ArrayList<SubTask>()

    // var swiped: Boolean = false
    val constant = Constant()
    var firestore = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth? = null
    lateinit var doc_ref: DocumentReference
    val sharedPref = SharedPref()

    // lateinit var new_subtask: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)

        val completed_btn = findViewById(R.id.completed_btn) as ImageView
        list_recycler_view = findViewById(R.id.list_recycler_view) as RecyclerView
        inbox_recycler = findViewById(R.id.inbox_recycler) as RecyclerView
        subtask_recycler = findViewById(R.id.subtask_recycler) as RecyclerView

        // val new_task = findViewById(R.id.new_task) as TextView
        val new_list_txt = findViewById(R.id.new_list_txt) as TextView
        // new_addlist = findViewById(R.id.new_addlist) as TextView
        // new_schedule = findViewById(R.id.new_schedule) as TextView
        // new_subtask = findViewById(R.id.new_subtask) as AppCompatEditText

        val swipe_txt = findViewById(R.id.swipe_txt) as TextView
        val plus_btn = findViewById(R.id.plus_btn) as TextView
        val add_new_subtask = findViewById(R.id.add_new_subtask) as TextView

        //  val cancle_btn = findViewById(R.id.cancle_btn) as ImageView

        add_list_txt = findViewById(R.id.add_list_txt) as TextView
        task_detail = findViewById(R.id.task_detail) as TextView

        schedule_txt = findViewById(R.id.schedule_txt) as TextView
        delete_done_layout = findViewById(R.id.delete_done_layout) as RelativeLayout
        task_list_layout = findViewById(R.id.task_list_layout) as RelativeLayout

        val cross_btn = findViewById(R.id.cross_btn) as ImageView
        val cross_btn2 = findViewById(R.id.cross_btn2) as ImageView
        task_recycler_view = findViewById(R.id.task_recycler_view) as RecyclerView


        val delete_task = findViewById(R.id.delete_task) as LinearLayout
        val mark_done = findViewById(R.id.mark_done) as LinearLayout
        //  val create_subtask = findViewById(R.id.create_subtask) as LinearLayout


        first_layout = findViewById(R.id.first_layout) as LinearLayout
        second_layout = findViewById(R.id.second_layout) as LinearLayout
        third_layout = findViewById(R.id.third_layout) as LinearLayout
        add_list = findViewById(R.id.add_list) as LinearLayout
        //  add_list_new = findViewById(R.id.add_list_new) as LinearLayout
        swipe_layout = findViewById(R.id.swipe_layout) as RelativeLayout
        scroll_1 = findViewById(R.id.scroll_1) as ScrollView
        scroll_2 = findViewById(R.id.scroll_2) as ScrollView


        //  val pullToRefresh = findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)

        firestore = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        user_id = mAuth!!.uid.toString()
        //  ref = firestore.collection(constant.USERS).document(user_id).collection(constant.TASKS).get()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        progressDialog = ProgressDialog(this@MainActivity)
        // create_subtask.isClickable = false


        mLayoutManager = LinearLayoutManager(this)
        (mLayoutManager as LinearLayoutManager?)?.setSmoothScrollbarEnabled(true)
        (mLayoutManager as LinearLayoutManager?)?.setOrientation(LinearLayoutManager.VERTICAL)

        dashboardListAdapter = DashboardListAdapter(myList, this)
        // list_recycler_view.adapter = dashboardListAdapter
        list_recycler_view?.setLayoutManager(mLayoutManager)
        list_recycler_view?.setAdapter(dashboardListAdapter)
        dashboardListAdapter?.notifyDataSetChanged()




        mLayoutManager = LinearLayoutManager(this)
        inbox_recycler.layoutManager = mLayoutManager
        inbox_adapter = Inbox_Adapter(inboxlist, this)
        inbox_recycler.adapter = inbox_adapter


        mLayoutManager4 = LinearLayoutManager(this)
        task_recycler_view.layoutManager = mLayoutManager4
        task_adapter = TaskListAdapter(myList, this)
        task_recycler_view.adapter = task_adapter



        mLayoutManager3 = LinearLayoutManager(this)
        subtask_recycler.layoutManager = mLayoutManager3

        subtsk_adapter = Subtask_Adapter(newsublist, "", "shade_16", this)
        subtask_recycler.adapter = subtsk_adapter


        /*   list_recycler_view.setOnClickListener {

               first_layout.visibility = View.GONE
               third_layout.visibility = View.VISIBLE
               second_layout.visibility = View.GONE
               swipe_layout.visibility = View.GONE
           }*/


        /* pullToRefresh.setOnRefreshListener {
            getTasks()
             // your code
            pullToRefresh.isRefreshing = false
        }*/


        new_list_txt.setOnClickListener {
            val intent = Intent(this, Createlist::class.java)
            startActivity(intent)
        }

        plus_btn.setOnClickListener {
            showNewTask()
        }

        add_new_subtask.setOnClickListener {
            showNewTask()
        }

        /* cancle_btn.setOnClickListener {
             create_task.visibility = View.GONE
             hideSoftKeyboard(new_subtask)
         }

         create_subtask.setOnClickListener {
             val txt = new_subtask.text.toString().trim()
             val parent = new_addlist.text.toString().trim()
             addSubtask(txt, parent)
         }*/


        /* new_subtask.addTextChangedListener(object : TextWatcher {

             override fun afterTextChanged(s: Editable) {
             }

             override fun beforeTextChanged(
                 s: CharSequence, start: Int, count: Int, after: Int
             ) {
             }

             override fun onTextChanged(
                 s: CharSequence, start: Int, before: Int, count: Int
             ) {
                 if (count > 0) {
                     //  val myToast = Toast.makeText(applicationContext,""+count,Toast.LENGTH_SHORT).show()
                     create_subtask.setBackgroundColor(resources.getColor(R.color.button_color))
                     create_subtask.isClickable = true
                 } else {
                     create_subtask.isClickable = false
                 }
             }
         });*/

        first_layout.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                /*Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()*/
                val intent = Intent(this@MainActivity, ScheduleTask::class.java)
                startActivity(intent)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                /*Toast.makeText(
                    this@MainActivity,
                    "Swipe Right gesture detected",
                    Toast.LENGTH_SHORT
                ).show()*/
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                /*Toast.makeText(this@MainActivity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                /*Toast.makeText(this@MainActivity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }
        })

        scroll_1.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                /*Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()*/
                val intent = Intent(this@MainActivity, ScheduleTask::class.java)
                startActivity(intent)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                /*Toast.makeText(
                    this@MainActivity,
                    "Swipe Right gesture detected",
                    Toast.LENGTH_SHORT
                ).show()*/
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                /*Toast.makeText(this@MainActivity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                /*Toast.makeText(this@MainActivity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()*/
            }
        })

        scroll_2.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                /*Toast.makeText(this@MainActivity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()*/
                val intent = Intent(this@MainActivity, ScheduleTask::class.java)
                startActivity(intent)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                /* Toast.makeText(
                     this@MainActivity,
                     "Swipe Right gesture detected",
                     Toast.LENGTH_SHORT
                 ).show()*/
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


        swipe_layout.setOnClickListener {
            // Check if we're running on Android 5.0 or higher
            /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                 val intent = Intent(this, ScheduleTask::class.java)
                 startActivity(intent)
                 // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
             } else {
             }*/


            val intent = Intent(this, ScheduleTask::class.java)
            startActivity(intent)
        }



        completed_btn.setOnClickListener {
            val intent = Intent(this, CompletedList_Activity::class.java)
            startActivity(intent)

        }

        /* get_started.setOnClickListener {
             delete_done_layout.visibility = View.VISIBLE

         }*/

        add_list.setOnClickListener {
            delete_done_layout.visibility = View.GONE
            task_list_layout.visibility = View.VISIBLE
        }

        /* add_list_new.setOnClickListener {
             delete_done_layout.visibility = View.GONE
             create_task.visibility = View.GONE
             hideSoftKeyboard(new_subtask);
             task_list_layout.visibility = View.VISIBLE

         }*/

        cross_btn.setOnClickListener {
            task_list_layout.visibility = View.GONE
            delete_done_layout.visibility = View.VISIBLE
        }

        cross_btn2.setOnClickListener {
            delete_done_layout.visibility = View.GONE
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
                            delete_done_layout.visibility = View.GONE
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




        val calendar = Calendar.getInstance()
        val today = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, -2)
        val yesterday = calendar.time

        val dateFormat: DateFormat = SimpleDateFormat("MMM dd, yyyy")
        sharedPref.save_value(this,constant.today    ,dateFormat.format(today))
        sharedPref.save_value(this,constant.tomorrow ,dateFormat.format(tomorrow))
        sharedPref.save_value(this,constant.yesterday,dateFormat.format(yesterday))


        getTasks()

    }

    open class OnSwipeTouchListener(c: Context?) :
        View.OnTouchListener {
        private val gestureDetector: GestureDetector
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(motionEvent)
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
                    if (abs(diffX) > abs(diffY)) {
                        if (abs(diffX) > SWIPE_THRESHOLD && abs(
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
                        if (abs(diffY) > SWIPE_THRESHOLD && abs(
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


    override fun onResume() {
        super.onResume()

    }


    fun getTasks() {//(v: View?)

        progressDialog.setCancelable(false)
        progressDialog.setMessage("connecting...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()



        firestore.collection(constant.USERS).document(user_id).collection(constant.TASKS)
            .get()
            .addOnSuccessListener { result ->

                myList.clear()
                for (document in result) {
                    //  Log.e("success", "${document.id} => ${document.data}")

                    val taskList: TaskList = TaskList()
                    taskList.setname(document.data.get("taskName").toString())
                    taskList.setcolorname(document.data.get("color").toString())
                    myList.add(taskList)

                }

                dashboardListAdapter = DashboardListAdapter(myList, this)
                list_recycler_view?.setAdapter(dashboardListAdapter)


                task_adapter = TaskListAdapter(myList, this)
                task_recycler_view?.setAdapter(task_adapter)


                dashboardListAdapter?.notifyDataSetChanged()
                progressDialog.cancel()
                getSubTasks()
            }
            .addOnFailureListener { exception ->
                Log.e("gettask_failure", "Error getting documents: ", exception)
                progressDialog.cancel()
            }
    }

    fun getSubTasks() {//(v: View?)
        sublist.clear()
        firestore.collection(constant.USERS).document(user_id).collection(constant.SUBTASKS)
            .get()
            .addOnSuccessListener { result ->

                sublist.clear()
                inboxlist.clear()
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

                    if (document.data.get(constant.list_name).toString().equals(constant.inbox)) {
                        inboxlist.add(subtaskList)
                    } else {
                        sublist.add(subtaskList)
                    }

                }

                if (inboxlist.size > 0 || sublist.size > 0) {


                    first_layout.visibility = View.GONE
                    third_layout.visibility = View.GONE

                    if (inboxlist.size > 0) {
                        second_layout.visibility = View.VISIBLE
                        inbox_adapter = Inbox_Adapter(inboxlist, this)
                        inbox_recycler?.setAdapter(inbox_adapter)
                        inbox_adapter?.notifyDataSetChanged()
                    } else {
                        second_layout.visibility = View.GONE
                        first_layout.visibility = View.VISIBLE
                        third_layout.visibility = View.GONE
                    }
                    subtsk_adapter = Subtask_Adapter(sublist, "", "shade_16", this)
                    subtask_recycler?.setAdapter(subtsk_adapter)
                    subtsk_adapter?.notifyDataSetChanged()

                } else {
                    second_layout.visibility = View.GONE
                    first_layout.visibility = View.VISIBLE
                    third_layout.visibility = View.GONE

                }


            }
            .addOnFailureListener { exception ->
                Log.e("failure", "Error getting documents: ", exception)
                progressDialog.cancel()
            }
    }


    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    // to delete od mark as done
    fun showDetail(node_id: String, txt: String, color: String) {
        delete_node_id = node_id

        task_detail.setText(txt)
        delete_done_layout.visibility = View.VISIBLE
    }

    fun showtask(title: String, color: String) {

        constant.b_Unscheduled = false
        constant.b_time =""
        if (color.equals("shade_0")) {
            if (inboxlist.size > 0) {
                second_layout.visibility = View.VISIBLE
                first_layout.visibility = View.GONE
                third_layout.visibility = View.GONE
            }else{
                second_layout.visibility = View.GONE
                first_layout.visibility = View.VISIBLE
                third_layout.visibility = View.GONE
            }

        } else {
            second_layout.visibility = View.GONE
            first_layout.visibility = View.GONE
            third_layout.visibility = View.VISIBLE

            newsublist.clear()
            completedlist.clear()
            for (i in sublist.indices) {
                 val name_list = sublist[i].getList_name().toString().trim()

                if (title.equals(name_list)) {

                    var name = sublist[i].getTask_status()
                    if (name!!.equals(constant.completed)) {

                        val subtaskList: SubTask = SubTask()
                        subtaskList.setList_name(sublist[i].getList_name().toString())
                        subtaskList.setStart_time(sublist[i].getStart_time().toString())
                        subtaskList.setEnd_time(sublist[i].getEnd_time().toString())
                        subtaskList.setDate(sublist[i].getDate().toString())
                        subtaskList.setTitle(sublist[i].getTitle().toString())
                        subtaskList.setTask_status(sublist[i].getTask_status().toString())
                        subtaskList.setNodeid(sublist[i].getNodeid().toString())
                        subtaskList.setColor(sublist[i].getColor().toString())
                        completedlist.add(subtaskList)

                    } else {
                        val subtaskList: SubTask = SubTask()
                        subtaskList.setList_name(sublist[i].getList_name().toString())
                        subtaskList.setStart_time(sublist[i].getStart_time().toString())
                        subtaskList.setEnd_time(sublist[i].getEnd_time().toString())
                        subtaskList.setDate(sublist[i].getDate().toString())
                        subtaskList.setTitle(sublist[i].getTitle().toString())
                        subtaskList.setTask_status(sublist[i].getTask_status().toString())
                        subtaskList.setNodeid(sublist[i].getNodeid().toString())
                        subtaskList.setColor(sublist[i].getColor().toString())
                        newsublist.add(subtaskList)
                    }
                }

            }


            if (newsublist.size > 0) {


                Collections.sort(newsublist, sortItems())

                println("Sorted in Ascending Order")

                for (d in newsublist) {
                    // Printing the sorted items from the List
                    System.out.println(d.getDate())
                }

                second_layout.visibility = View.GONE
                first_layout.visibility = View.GONE
                third_layout.visibility = View.VISIBLE
                subtsk_adapter = Subtask_Adapter(newsublist, title, color, this)
                subtask_recycler?.setAdapter(subtsk_adapter)
                subtsk_adapter?.notifyDataSetChanged()

            } else {
                second_layout.visibility = View.GONE
                first_layout.visibility = View.VISIBLE
                third_layout.visibility = View.GONE
            }
        }


    }


    fun showNewTask() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val intent = Intent(this, ScheduleTask::class.java)
            startActivity(intent)
            // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            val intent = Intent(this, ScheduleTask::class.java)
            startActivity(intent)
        }
    }


    fun showListName(list_name: String, color: String) {
        var list_color = color
        Log.e("listname", list_name)
        task_list_layout.visibility = View.GONE
        delete_done_layout.visibility = View.VISIBLE
        add_list_txt.setText(list_name)

    }


    internal  class sortItems : Comparator<SubTask?> {
        // Method of this class
        // @Override
        override fun compare(a: SubTask?, b: SubTask?): Int {

            // Returning the value after comparing the objects
            // this will sort the data in Ascending order
            return a?.getDate().toString().compareTo(b?.getDate().toString())
        }
    }


}