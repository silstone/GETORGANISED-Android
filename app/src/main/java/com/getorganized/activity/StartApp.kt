package com.getorganized.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R

class StartApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_app)
        val start = findViewById(R.id.start) as TextView
        // set on-click listener
        start.setOnClickListener {
            val intent = Intent(this, Createlist::class.java)
            startActivity(intent)
            finish()
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }
}