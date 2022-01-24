package com.getorganized.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R

class SettingScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        val cross_btn = findViewById(R.id.cross_btn) as ImageView

        /*val swipe_txt = findViewById(R.id.swipe_txt) as TextView
        val save = findViewById(R.id.save) as TextView
        val cancle = findViewById(R.id.cancle) as TextView
        val new_list_txt = findViewById(R.id.new_list_txt) as TextView
        val add_task_btn = findViewById(R.id.add_task_btn) as ImageView
        val start_time = findViewById(R.id.start_time) as TextView
        val end_time = findViewById(R.id.end_time) as TextView
        val add_list_txt = findViewById(R.id.add_list_txt) as TextView
        val schedule_txt = findViewById(R.id.schedule_txt) as TextView*/

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