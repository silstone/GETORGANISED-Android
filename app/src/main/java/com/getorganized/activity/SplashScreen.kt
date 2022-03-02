package com.getorganized.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref

class SplashScreen : AppCompatActivity() {

    val sharedPref = SharedPref()
    val constant = Constant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val login = sharedPref.get_bool_value(this, constant.USER_LOGIN)

        if (login) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {

            Handler().postDelayed({
                val intent = Intent(this, Welcome::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}