package com.getorganized.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref
import com.google.firebase.auth.FirebaseAuth

class SettingScreen : AppCompatActivity() {

    val sharedPref = SharedPref()
    val scheduleTask = ScheduleTask()
    val mainActivity = MainActivity()
    val constant = Constant()
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mAuth = FirebaseAuth.getInstance()


        val cross_btn = findViewById(R.id.cross_btn) as ImageView
        val user_email = findViewById(R.id.user_email) as TextView
        val sign_out = findViewById(R.id.sign_out) as TextView


        val s_email = sharedPref.get_value(this, constant.USER_EMAIL)
        user_email.setText(s_email)


        /*
        val save = findViewById(R.id.save) as TextView
        val cancle = findViewById(R.id.cancle) as TextView
        val new_list_txt = findViewById(R.id.new_list_txt) as TextView
        val add_task_btn = findViewById(R.id.add_task_btn) as ImageView
        val start_time = findViewById(R.id.start_time) as TextView
        val end_time = findViewById(R.id.end_time) as TextView
        val add_list_txt = findViewById(R.id.add_list_txt) as TextView
        val schedule_txt = findViewById(R.id.schedule_txt) as TextView*/

        sign_out.setOnClickListener {
            mAuth!!.signOut()
            sharedPref.clear_data(this@SettingScreen)
            val intent = Intent(this, LoginRegister::class.java)
            startActivity(intent)
            scheduleTask.finish()
            mainActivity.finish()
            finish()
            Toast.makeText(applicationContext,"SignOut succesfully",Toast.LENGTH_SHORT).show()
        }
        cross_btn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            } else {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
        }

    }
}