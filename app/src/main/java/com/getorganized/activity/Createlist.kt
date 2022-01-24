package com.getorganized.activity

import android.annotation.SuppressLint
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
import androidx.cardview.widget.CardView
import com.getorganized.R
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap


 class Createlist : AppCompatActivity() {

    var s_txt: String = ""

    //var myList: List<TaskList> = java.util.ArrayList<TaskList>()
    //val list: ArrayList<String> = arrayListOf<String>()
    val hashMap:HashMap<String, String> = HashMap<String, String>()

      lateinit var event_list: HashMap<String,String>
     //  lateinit var  user: HashMap<Int,String>

    private val db = FirebaseFirestore.getInstance()
    val sharedPref = SharedPref()
    val constant = Constant()
    var s_email=""
    var selected_color=""

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_list)
        val plus_btn = findViewById(R.id.plus_btn) as TextView
        val card_text = findViewById(R.id.card_text) as TextView
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
        val cardview = findViewById(R.id.cardview) as CardView

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val names = listOf("Inbox", "work", "Hello", "Shop")

        for (i in names) {
            hashMap.put("0" , "Inbox")
            hashMap.put("1" , "work")
            hashMap.put("2" , "Person")
            hashMap.put("3" , "Shop")
        }




         event_list = hashMapOf(
            "shade_1" to "Inbox",
            "shade_2" to "work",
            "shade_3" to "Person",
            "shade_4" to "Shop"
        )





        list_name.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Your action on done
                create_layout.visibility = View.GONE
                hideSoftKeyboard(list_name)
                s_txt = list_name.text.toString()+","+selected_color;
                if (s_txt.length > 0) {
                    cardview.visibility = View.VISIBLE
                    next_button.visibility = View.VISIBLE
                } else {
                    cardview.visibility = View.GONE
                    next_button.visibility = View.GONE
                }
                card_text.setText(s_txt)
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
            cardview.visibility = View.VISIBLE
            next_button.visibility = View.VISIBLE
            hideSoftKeyboard(list_name)
            s_txt = list_name.text.toString();
            hashMap.put(hashMap.size.toString(),s_txt)

            event_list.put("shade_5",s_txt)
            card_text.setText(s_txt)
            list_name.setText("")
            confirm_layout.setBackgroundColor(resources.getColor(R.color.txt_gray))
            save_to_list()

        }

        bacup_layout.setOnClickListener {
          //  create_layout.visibility = View.GONE
        }


        next_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("mylist", hashMap);
            startActivity(intent)

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
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_1))
            shade_1.setImageDrawable(resources.getDrawable(R.drawable.shade_1_icon))
            shade_1.setPadding(0, 0, 0, 0)
            Log.e("shade_1 ", "")
            selected_color= shade_1.toString()
        }
        shade_2.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_2))
            shade_2.setImageDrawable(resources.getDrawable(R.drawable.shade_2_icon))
            shade_2.setPadding(0, 0, 0, 0)
            Log.e("shade_2 ", "")
            selected_color= shade_2.toString()
        }
        shade_3.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_3))
            shade_3.setImageDrawable(resources.getDrawable(R.drawable.shade_3_icon))
            shade_3.setPadding(0, 0, 0, 0)
            Log.e("shade_3 ", "")
            selected_color= shade_3.toString()
        }
        shade_4.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_4))
            shade_4.setImageDrawable(resources.getDrawable(R.drawable.shade_4_icon))
            shade_4.setPadding(0, 0, 0, 0)
            Log.e("shade_4 ", "")
            selected_color= shade_4.toString()
        }
        shade_5.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_5))
            shade_5.setImageDrawable(resources.getDrawable(R.drawable.shade_5_icon))
            shade_5.setPadding(0, 0, 0, 0)
            Log.e("shade_5 ", "")
            selected_color= shade_5.toString()
        }
        shade_6.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_6))
            shade_6.setImageDrawable(resources.getDrawable(R.drawable.shade_6_icon))
            shade_6.setPadding(0, 0, 0, 0)
            Log.e("shade_6 ", "")
            selected_color= shade_6.toString()
        }
        shade_7.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_7))
            shade_7.setImageDrawable(resources.getDrawable(R.drawable.shade_7_icon))
            shade_7.setPadding(0, 0, 0, 0)
            Log.e("shade_7 ", "")
            selected_color= shade_7.toString()
        }
        shade_8.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_8))
            shade_8.setImageDrawable(resources.getDrawable(R.drawable.shade_8_icon))
            shade_8.setPadding(0, 0, 0, 0)
            Log.e("shade_8 ", "")
            selected_color= shade_8.toString()
        }
        shade_9.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_9))
            shade_9.setImageDrawable(resources.getDrawable(R.drawable.shade_9_icon))
            shade_9.setPadding(0, 0, 0, 0)
            Log.e("shade_9 ", "")
            selected_color= shade_9.toString()
        }
        shade_10.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_10))
            shade_10.setImageDrawable(resources.getDrawable(R.drawable.shade_10_icon))
            shade_10.setPadding(0, 0, 0, 0)
            Log.e("shade_10", "")
            selected_color= shade_10.toString()
        }
        shade_11.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_11))
            shade_11.setImageDrawable(resources.getDrawable(R.drawable.shade_11_icon))
            shade_11.setPadding(0, 0, 0, 0)
            Log.e("shade_11", "")
            selected_color= shade_11.toString()
        }
        shade_12.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_12))
            shade_12.setImageDrawable(resources.getDrawable(R.drawable.shade_12_icon))
            shade_12.setPadding(0, 0, 0, 0)
            Log.e("shade_12", "")
            selected_color= shade_12.toString()
        }
        shade_13.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_13))
            shade_13.setImageDrawable(resources.getDrawable(R.drawable.shade_13_icon))
            shade_13.setPadding(0, 0, 0, 0)
            Log.e("shade_13", "")
            selected_color= shade_13.toString()
        }
        shade_14.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_14))
            shade_14.setImageDrawable(resources.getDrawable(R.drawable.shade_14_icon))
            shade_14.setPadding(0, 0, 0, 0)
            Log.e("shade_14", "")
            selected_color= shade_14.toString()
        }
        shade_15.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_15))
            shade_15.setImageDrawable(resources.getDrawable(R.drawable.shade_15_icon))
            shade_15.setPadding(0, 0, 0, 0)
            Log.e("shade_15", "")
            selected_color= shade_15.toString()
        }
        shade_16.setOnClickListener {
            cardview.setCardBackgroundColor(resources.getColor(R.color.shade_16))
            shade_16.setImageDrawable(resources.getDrawable(R.drawable.shade_16_icon))
            shade_16.setPadding(0, 0, 0, 0)
            Log.e("shade_16", "")
            selected_color= shade_16.toString()
        }



    }

    private fun save_to_list() {

        s_email = sharedPref.getemail(this).toString()

// Add a new document with a generated ID

        db.collection("USERS").document(s_email).collection(constant.USER_EVENT_LIST)
            .add(event_list)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
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





