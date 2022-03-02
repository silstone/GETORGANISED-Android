package com.getorganized.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref

class StartApp : AppCompatActivity() {
    val sharedPref = SharedPref()
    val constant = Constant()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_app)
        val start = findViewById(R.id.start) as TextView
        // set on-click listener
        start.setOnClickListener {
            val login = sharedPref.get_bool_value(this, constant.USER_LOGIN)

            if (login) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, Createlist::class.java)
                startActivity(intent)
                finish()
            }

        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }
}