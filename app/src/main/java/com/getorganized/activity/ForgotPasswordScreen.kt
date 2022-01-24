package com.getorganized.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordScreen : AppCompatActivity() {


    lateinit var email_top: TextView
    lateinit var send_link: TextView
    lateinit var back_btn: TextView
    lateinit var email_ed: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        send_link = findViewById(R.id.send_link) as TextView
        back_btn = findViewById(R.id.back_btn) as TextView
        email_ed = findViewById(R.id.email_ed) as EditText
        email_top = findViewById(R.id.email_top) as TextView


        email_ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (email_ed.text.length > 0) {
                    email_top.visibility = View.VISIBLE
                    send_link.setBackground(resources.getDrawable(R.drawable.btn_selected_background))
                    send_link.isEnabled = true
                } else {
                    email_top.visibility = View.GONE
                    send_link.setBackground(resources.getDrawable(R.drawable.btn_unselected_background))
                    send_link.isEnabled = false

                }
            }
        })

        back_btn.setOnClickListener {
            finish()
        }


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        send_link.setOnClickListener {

            val s_email: String = email_ed.getText().toString().trim()

            /*   var email= email_ed.toString().trim()
               val intent = Intent(Intent.ACTION_SEND)
               val recipients = arrayOf(email)
               intent.putExtra(Intent.EXTRA_EMAIL, recipients)
               intent.putExtra(Intent.EXTRA_SUBJECT, "Reset your password!")
               intent.putExtra(Intent.EXTRA_TEXT, "")
               //  intent.putExtra(Intent.EXTRA_CC,"@gmail.com");
               //  intent.putExtra(Intent.EXTRA_CC,"@gmail.com");
               intent.type = "text/html"
               intent.setPackage("com.google.android.gm")
               startActivity(Intent.createChooser(intent, "Send mail"))
   */

            FirebaseAuth.getInstance().sendPasswordResetEmail(s_email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Reset password email been sent to your email address")
                        Log.e("Forgot_password", "Email sent.")
                        finish()
                    }else{
                        showToast(task.exception!!.localizedMessage.toString())
                    }
                } }
    }


    private fun showToast(s: String) {
        val toast = Toast.makeText(applicationContext, "" + s, Toast.LENGTH_LONG)
        toast.show()
    }


}