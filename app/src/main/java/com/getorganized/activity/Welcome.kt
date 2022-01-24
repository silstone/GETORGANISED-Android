package com.getorganized.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R



class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
        val get_started = findViewById(R.id.get_started) as TextView
        // set on-click listener
        get_started.setOnClickListener {
            val intent = Intent(this, SelectionScreen::class.java)
            startActivity(intent)
            finish()
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }
}