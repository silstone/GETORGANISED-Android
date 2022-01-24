package com.getorganized.activity

import android.media.Image
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R

class TermsPrivacy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terms_privacy)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val back = findViewById(R.id.back) as ImageView
        val terms_txt = findViewById(R.id.terms_txt) as TextView

        back.setOnClickListener {
            finish() }

    }


}