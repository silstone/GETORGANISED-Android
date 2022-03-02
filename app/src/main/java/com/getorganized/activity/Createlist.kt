package com.getorganized.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getorganized.R
import com.getorganized.adapters.CreateListAdapter
import com.getorganized.model_classes.TaskList
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.HashMap


class Createlist : AppCompatActivity() {

    var s_txt: String = ""

    var myList: ArrayList<TaskList> = ArrayList<TaskList>()
    //val list: ArrayList<String> = arrayListOf<String>()

    private lateinit var progressDialog: ProgressDialog

    private lateinit var linearLayoutManager: LinearLayoutManager
    var adapter = CreateListAdapter()
    lateinit var task_recycler: RecyclerView
    //  lateinit var  user: HashMap<Int,String>

    private val db = FirebaseFirestore.getInstance()
    val sharedPref = SharedPref()
    val constant = Constant()
    var s_email = ""
    var selected_color = ""

    var b_shade_1 = false
    var b_shade_2 = false
    var b_shade_3 = false
    var b_shade_4 = false
    var b_shade_5 = false
    var b_shade_6 = false
    var b_shade_7 = false
    var b_shade_8 = false
    var b_shade_9 = false
    var b_shade_10 = false
    var b_shade_11 = false
    var b_shade_12= false
    var b_shade_13= false
    var b_shade_14= false
    var b_shade_15= false
    var b_shade_16= false




    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_list)
        val plus_btn = findViewById(R.id.plus_btn) as TextView
//        val card_text = findViewById(R.id.card_text) as TextView
        val next_button = findViewById(R.id.next_button) as TextView


        val shade_1 = findViewById(R.id.shade_1) as ImageView
        val shade_2 = findViewById(R.id.shade_2) as ImageView
        val shade_3 = findViewById(R.id.shade_3) as ImageView
        val shade_4 = findViewById(R.id.shade_4) as ImageView
        val shade_5 = findViewById(R.id.shade_5) as ImageView
        val shade_6 = findViewById(R.id.shade_6) as ImageView
        val shade_7 = findViewById(R.id.shade_7) as ImageView
        val shade_8 = findViewById(R.id.shade_8) as ImageView
        val shade_9 = findViewById(R.id.shade_9) as ImageView
        val shade_10 = findViewById(R.id.shade_10) as ImageView
        val shade_11 = findViewById(R.id.shade_11) as ImageView
        val shade_12 = findViewById(R.id.shade_12) as ImageView
        val shade_13 = findViewById(R.id.shade_13) as ImageView
        val shade_14 = findViewById(R.id.shade_14) as ImageView
        val shade_15 = findViewById(R.id.shade_15) as ImageView
        val shade_16 = findViewById(R.id.shade_16) as ImageView

        val confirm_layout = findViewById(R.id.confirm_layout) as LinearLayout
        val bacup_layout = findViewById(R.id.bacup_layout) as LinearLayout
        val create_layout = findViewById(R.id.create_layout) as RelativeLayout
        val list_name = findViewById(R.id.list_name) as AppCompatEditText
       // val //cardview = findViewById(R.id.//cardview) as //cardview

        task_recycler = findViewById(R.id.task_recycler) as RecyclerView



        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        linearLayoutManager = LinearLayoutManager(this)
        task_recycler.layoutManager = linearLayoutManager

        adapter = CreateListAdapter(myList, this)
        task_recycler.adapter = adapter
        progressDialog = ProgressDialog(this@Createlist)


        /* val names = listOf("Inbox", "work", "Hello", "Shop")

         for (i in names) {
             hashMap.put("0", "Inbox")
             hashMap.put("1", "work")
             hashMap.put("2", "Person")
             hashMap.put("3", "Shop")

         }*/


        val taskList: TaskList = TaskList()
        taskList.setname("Inbox")
        taskList.setcolorname("shade_0")
        myList.add(taskList)

      /*  event_list = hashMapOf(
            "taskName" to "Shop",
        )*/





        list_name.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Your action on done
                create_layout.visibility = View.GONE
                hideSoftKeyboard(list_name)
                s_txt = list_name.text.toString() + "," + selected_color;
                if (s_txt.length > 0) {
                   // //cardview.visibility = View.VISIBLE
                    next_button.visibility = View.VISIBLE
                } else {
                   // //cardview.visibility = View.GONE
                    next_button.visibility = View.GONE
                }
              //  card_text.setText(s_txt)
                list_name.setText("")

                true
            } else false
        })


        // set on-click listener
        plus_btn.setOnClickListener {

            create_layout.visibility = View.VISIBLE
            //  list_name.requestFocus()
            showSoftKeyboard(list_name);
            val toast = Toast.makeText(
                applicationContext,
                "Select, deselect colors will perform with functionality",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }

        confirm_layout.setOnClickListener {
            create_layout.visibility = View.GONE
           // //cardview.visibility = View.VISIBLE
            next_button.visibility = View.VISIBLE
            hideSoftKeyboard(list_name)
            s_txt = list_name.text.toString();


            val taskList: TaskList = TaskList()
            taskList.setname(s_txt)
            taskList.setcolorname(selected_color)
            myList.add(taskList)

           // hashMap.put(hashMap.size.toString(), s_txt)
           // event_list.put("taskName", s_txt)
           // card_text.setText(s_txt)
            list_name.setText("")
            confirm_layout.setBackgroundColor(resources.getColor(R.color.txt_gray))
            adapter.notifyDataSetChanged()

        }

        bacup_layout.setOnClickListener {
            //  create_layout.visibility = View.GONE
        }


        next_button.setOnClickListener {

            progressDialog.setCancelable(false)
            progressDialog.setMessage("Creating...")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.show()

            val user = FirebaseAuth.getInstance().currentUser!!
            save_to_list(user.uid)

        }

        list_name.addTextChangedListener(object : TextWatcher {

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
                    confirm_layout.setBackgroundColor(resources.getColor(R.color.button_color))
                }
            }
        });



        shade_1.setOnClickListener {
          //  //cardview.setCardBackgroundColor(resources.getColor(R.color.shade_1))

            if (b_shade_1){
                shade_1.setImageDrawable(resources.getDrawable(R.drawable.shade_1_unselected))
                shade_1.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_1 = false
            }else{
                shade_1.setImageDrawable(resources.getDrawable(R.drawable.shade_1_icon))
                shade_1.setPadding(0, 0, 0, 0)
                Log.e("shade_1 ", "")
                selected_color = "shade_1"
                b_shade_1 = true
            }




        }
        shade_2.setOnClickListener {


            if (b_shade_2){
                shade_2.setImageDrawable(resources.getDrawable(R.drawable.shade_2_unselected))
                shade_2.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_2 = false
            }else{
                shade_2.setImageDrawable(resources.getDrawable(R.drawable.shade_2_icon))
                shade_2.setPadding(0, 0, 0, 0)
                Log.e("shade_2 ", "")
                selected_color = "shade_2"
                b_shade_2 = true
            }

        }
        shade_3.setOnClickListener {


            if (b_shade_3){
                shade_3.setImageDrawable(resources.getDrawable(R.drawable.shade_3_unselected))
                shade_3.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_3 = false
            }else{
                shade_3.setImageDrawable(resources.getDrawable(R.drawable.shade_3_icon))
                shade_3.setPadding(0, 0, 0, 0)
                Log.e("shade_3 ", "")
                selected_color = "shade_"
                b_shade_3 = true
            }
        }
        shade_4.setOnClickListener {


            if (b_shade_4){
                shade_4.setImageDrawable(resources.getDrawable(R.drawable.shade_4_unselected))
                shade_4.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_4 = false
            }else{
                shade_4.setImageDrawable(resources.getDrawable(R.drawable.shade_4_icon))
                shade_4.setPadding(0, 0, 0, 0)
                Log.e("shade_4 ", "")
                selected_color = "shade_4"
                b_shade_4 = true
            }


        }
        shade_5.setOnClickListener {

            if (b_shade_5){
                shade_5.setImageDrawable(resources.getDrawable(R.drawable.shade_5_unselected))
                shade_5.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_5 = false
            }else{
                shade_5.setImageDrawable(resources.getDrawable(R.drawable.shade_5_icon))
                shade_5.setPadding(0, 0, 0, 0)
                Log.e("shade_5", "")
                selected_color = "shade_5"
                b_shade_5 = true
            }
        }
        shade_6.setOnClickListener {


            if (b_shade_6){
                shade_6.setImageDrawable(resources.getDrawable(R.drawable.shade_6_unselected))
                shade_6.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_6 = false
            }else{
                shade_6.setImageDrawable(resources.getDrawable(R.drawable.shade_6_icon))
                shade_6.setPadding(0, 0, 0, 0)
                Log.e("shade_6", "")
                selected_color = "shade_6"
                b_shade_6 = true
            }
        }
        shade_7.setOnClickListener {


            if (b_shade_7){
                shade_7.setImageDrawable(resources.getDrawable(R.drawable.shade_7_unselected))
                shade_7.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_7 = false
            }else{
                shade_7.setImageDrawable(resources.getDrawable(R.drawable.shade_7_icon))
                shade_7.setPadding(0, 0, 0, 0)
                Log.e("shade_7 ", "")
                selected_color = "shade_7"
                b_shade_7 = true
            }
        }
        shade_8.setOnClickListener {

            if (b_shade_8){
                shade_8.setImageDrawable(resources.getDrawable(R.drawable.shade_8_unselected))
                shade_8.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_8 = false
            }else{
                shade_8.setImageDrawable(resources.getDrawable(R.drawable.shade_8_icon))
                shade_8.setPadding(0, 0, 0, 0)
                Log.e("shade_8 ", "")
                selected_color = "shade_8"
                b_shade_8 = true
            }
        }
        shade_9.setOnClickListener {

            if (b_shade_9){
                shade_9.setImageDrawable(resources.getDrawable(R.drawable.shade_9_unselected))
                shade_9.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_9 = false
            }else{
                shade_9.setImageDrawable(resources.getDrawable(R.drawable.shade_9_icon))
                shade_9.setPadding(0, 0, 0, 0)
                Log.e("shade_9 ", "")
                selected_color = "shade_9"
                b_shade_9 = true
            }


        }
        shade_10.setOnClickListener {
            selected_color = "shade_10"//.toString()

            if (b_shade_10){
                shade_10.setImageDrawable(resources.getDrawable(R.drawable.shade_10_unselected))
                shade_10.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_10 = false
            }else{
                shade_10.setImageDrawable(resources.getDrawable(R.drawable.shade_10_icon))
                shade_10.setPadding(0, 0, 0, 0)
                Log.e("shade_10 ", "")
                selected_color = "shade_10"
                b_shade_10 = true
            }


        }
        shade_11.setOnClickListener {

            if (b_shade_11){
                shade_11.setImageDrawable(resources.getDrawable(R.drawable.shade_11_unselected))
                shade_11.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_11 = false
            }else{
                shade_11.setImageDrawable(resources.getDrawable(R.drawable.shade_11_icon))
                shade_11.setPadding(0, 0, 0, 0)
                Log.e("shade_11", "")
                selected_color = "shade_11"
                b_shade_11 = true
            }


        }
        shade_12.setOnClickListener {


            if (b_shade_12){
                shade_12.setImageDrawable(resources.getDrawable(R.drawable.shade_12_unselected))
                shade_12.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_12 = false
            }else{
                shade_12.setImageDrawable(resources.getDrawable(R.drawable.shade_12_icon))
                shade_12.setPadding(0, 0, 0, 0)
                Log.e("shade_12 ", "")
                selected_color = "shade_12"
                b_shade_12 = true
            }


        }
        shade_13.setOnClickListener {


            if (b_shade_13){
                shade_13.setImageDrawable(resources.getDrawable(R.drawable.shade_13_unselected))
                shade_13.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_13 = false
            }else{
                shade_13.setImageDrawable(resources.getDrawable(R.drawable.shade_13_icon))
                shade_13.setPadding(0, 0, 0, 0)
                Log.e("shade_13", "")
                selected_color = "shade_13"
                b_shade_13 = true
            }


        }
        shade_14.setOnClickListener {


            if (b_shade_14){
                shade_14.setImageDrawable(resources.getDrawable(R.drawable.shade_14_unselected))
                shade_14.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_14 = false
            }else{
                shade_14.setImageDrawable(resources.getDrawable(R.drawable.shade_14_icon))
                shade_14.setPadding(0, 0, 0, 0)
                Log.e("shade_14 ", "")
                selected_color = "shade_14"
                b_shade_14 = true
            }


        }
        shade_15.setOnClickListener {

            if (b_shade_15){
                shade_15.setImageDrawable(resources.getDrawable(R.drawable.shade_15_unselected))
                shade_15.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_15 = false
            }else{
                shade_15.setImageDrawable(resources.getDrawable(R.drawable.shade_15_icon))
                shade_15.setPadding(0, 0, 0, 0)
                Log.e("shade_15 ", "")
                selected_color = "shade_15"
                b_shade_15 = true
            }


        }
        shade_16.setOnClickListener {
            //cardview.setCardBackgroundColor(resources.getColor(R.color.shade_16))


            if (b_shade_1){
                shade_16.setImageDrawable(resources.getDrawable(R.drawable.shade_16_unselected))
                shade_16.setPadding(12,12,12,12)
                selected_color = ""
                b_shade_6 = false
            }else{
                shade_16.setImageDrawable(resources.getDrawable(R.drawable.shade_16_icon))
                shade_16.setPadding(0, 0, 0, 0)
                Log.e("shade_16", "")
                selected_color = "shade_16"//.toString()
                b_shade_16 = true
            }
        }


    }

    private fun save_to_list(uid: String) {

        s_email = sharedPref.get_value(this, constant.USER_EMAIL).toString()
        Log.e("user_id", uid)



        for (i in 0..myList.size - 1) {

             val event_list: HashMap<String, String> = HashMap()
            event_list.put("taskName", myList.get(i).getname().toString())
            event_list.put("color", myList.get(i).getcolorname().toString())

             db.collection(constant.USERS).document(uid).collection(constant.TASKS)
                .add(event_list)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.d("TAG", "Error adding document", e)
                }
        }///for loop end

        progressDialog.cancel()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onBackPressed() {
        super.onBackPressed()

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



}







