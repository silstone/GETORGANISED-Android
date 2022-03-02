package com.getorganized.activity

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.adapters.Completed_Adapter
import com.getorganized.adapters.Inbox_Adapter
import com.getorganized.adapters.Subtask_Adapter
import com.getorganized.model_classes.SubTask
import com.getorganized.utils.Constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CompletedList_Activity : AppCompatActivity() {

    lateinit var complete_recycler: RecyclerView
    var completedlist: ArrayList<SubTask> = ArrayList<SubTask>()
    var mLayoutManager: RecyclerView.LayoutManager? = null

    var completed_adapter: Completed_Adapter? = null
    var mainActivity = MainActivity()
    val constant = Constant()
    var firestore = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth? = null
    var user_id = ""
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.completed_list_activity)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        val cross_btn = findViewById(R.id.cross_btn) as ImageView
        complete_recycler = findViewById(R.id.complete_recycler) as RecyclerView


        firestore = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        user_id = mAuth!!.uid.toString()
        progressDialog = ProgressDialog(this@CompletedList_Activity)

        mLayoutManager = LinearLayoutManager(this)
        complete_recycler.layoutManager = mLayoutManager

        completed_adapter = Completed_Adapter(completedlist, this)
        complete_recycler?.setAdapter(completed_adapter)
        completed_adapter?.notifyDataSetChanged()


        cross_btn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            } else {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
        }






        getSubTasks()
    }


    fun getSubTasks() {//(v: View?)

        progressDialog.setCancelable(false)
        progressDialog.setMessage("Please wait...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()

        firestore.collection(constant.USERS).document(user_id).collection(constant.SUBTASKS)
            .get()
            .addOnSuccessListener { result ->

                completedlist.clear()
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

                    var name = document.data.get(constant.task_status).toString()

                    if (name.equals(constant.completed)) {
                        completedlist.add(subtaskList)
                    }

                }
                progressDialog.cancel()
                completed_adapter = Completed_Adapter(completedlist, this)
                complete_recycler?.setAdapter(completed_adapter)
                completed_adapter?.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
                Log.e("failure", "Error getting documents: ", exception)
                progressDialog.cancel()
            }
    }

}