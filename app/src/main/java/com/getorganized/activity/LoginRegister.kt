package com.getorganized.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R
import com.getorganized.utils.Constant
import com.getorganized.utils.SharedPref
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class LoginRegister : AppCompatActivity() {

    var s_email = ""
    var s_pass = ""
    var s_confirm_pass = ""
    var b_registr: Boolean = false
    var b_email: Boolean = false
    var b_pass: Boolean = false
    var b_confm_pass: Boolean = false
    private var doubleBackToExitPressedOnce = false
    lateinit var v1: View
    lateinit var v2: View
    lateinit var confirm_layout: LinearLayout

    lateinit var change_login: TextView
    lateinit var change_register: TextView
    lateinit var pass_top: TextView
    lateinit var email_top: TextView
    lateinit var confirm_pass_top: TextView

    lateinit var forgot_password: TextView
    lateinit var by_click: TextView
    lateinit var terms: TextView

    lateinit var password_ed: EditText
    lateinit var confirm_password_ed: EditText
    lateinit var email_ed: EditText

    var db = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth? = null

    private lateinit var analytics: FirebaseAnalytics
    val constant = Constant()
    var emailPattern = ""

    lateinit var login_register_button: TextView

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_register)


        change_login = findViewById(R.id.login) as TextView
        change_register = findViewById(R.id.register) as TextView
        val back_btn = findViewById(R.id.back_btn) as TextView

        password_ed = findViewById(R.id.password_ed) as EditText
        confirm_password_ed = findViewById(R.id.confirm_password_ed) as EditText
        email_ed = findViewById(R.id.email_ed) as EditText

        pass_top         = findViewById(R.id.pass_top) as TextView
        email_top        = findViewById(R.id.email_top) as TextView
        confirm_pass_top = findViewById(R.id.confirm_pass_top) as TextView


        pass_top.visibility = View.GONE
        email_top.visibility = View.GONE
        confirm_pass_top.visibility = View.GONE


        login_register_button = findViewById(R.id.login_register_button) as TextView
        forgot_password = findViewById(R.id.forgot_password) as TextView
        by_click = findViewById(R.id.by_click) as TextView
        terms = findViewById(R.id.terms) as TextView


        v1 = findViewById(R.id.v1) as View
        v2 = findViewById(R.id.v2) as View
        confirm_layout = findViewById(R.id.confirm_layout) as LinearLayout

        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        analytics = Firebase.analytics



        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

       // googleSignInClient = GoogleSignIn.getClient(this, gso)


        email_ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (email_ed.text.length > 0) {
                    email_top.visibility = View.VISIBLE
                    b_email = true
                    validate(count)
                } else {
                    email_top.visibility = View.GONE
                  //  email_ed.setHint(resources.getString(R.string.email_address))
                    b_email = false
                    validate(count)
                    login_register_button.setBackground(resources.getDrawable(R.drawable.btn_unselected_background))

                }
            }
        })

        password_ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (password_ed.text.length > 0) {
                    pass_top.visibility = View.VISIBLE
                    b_pass = true
                    validate(count)
                } else {
                    pass_top.visibility = View.GONE
                    b_pass = false
                    validate(count)
                    login_register_button.setBackground(resources.getDrawable(R.drawable.btn_unselected_background))

                }
            }
        })

        confirm_password_ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (confirm_password_ed.text.length > 0) {
                    pass_top.visibility = View.VISIBLE
                    b_confm_pass = true
                    validate(count)
                } else {
                    pass_top.visibility = View.GONE
                    b_confm_pass = false
                    validate(count)
                    login_register_button.setBackground(resources.getDrawable(R.drawable.btn_unselected_background))

                }
            }
        })

        change_login.setOnClickListener {
            change_to_login()
        }


        change_register.setOnClickListener {
            change_to_signup()
        }




        login_register_button.setOnClickListener {
            s_email = email_ed.getText().toString().trim { it <= ' ' }
            s_pass = password_ed.getText().toString().trim { it <= ' ' }
            s_confirm_pass = confirm_password_ed.getText().toString().trim { it <= ' ' }
            emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            if (!isOnline(this)) {
                showToast(resources.getString(R.string.internet))
            } else {
                if (s_email != "" || s_email == null) {
                    if (isEmailValid(s_email)) {
                        if (s_pass != "" || s_pass == null) {
                            if (s_pass != "" || s_pass == null) {

                                if (b_registr) {
                                    if (s_pass.equals(s_confirm_pass)) {
                                        sighup_fun(s_email, s_pass);
                                    } else {
                                        showToast(resources.getString(R.string.plz_both_pass))
                                    }
                                } else {
                                    login_fun(s_email, s_pass);
                                }

                            } else {
                                showToast(resources.getString(R.string.plz_enter_confirm_pass))
                            }
                        } else {
                            showToast(resources.getString(R.string.plz_enter_pass))
                        }
                    } else {
                        showToast(resources.getString(R.string.plz_enter_valid_email))
                    }
                } else {
                    showToast(resources.getString(R.string.plz_enter_email))
                }

            }
        }

        terms.setOnClickListener {
            val intent = Intent(this, TermsPrivacy::class.java)
            startActivity(intent)
        }


        back_btn.setOnClickListener {
            finish()
        }

        forgot_password.setOnClickListener {
            val intent = Intent(this, ForgotPasswordScreen::class.java)
            startActivity(intent)
        }


        /* confirm_password_ed.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
             if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                 login_register_button.performClick()
             }
             false
         })*/



        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun change_to_login() {

        confirm_layout.visibility = View.GONE
        change_login.setTextColor(resources.getColor(R.color.blue))
        change_register.setTextColor(resources.getColor(R.color.txt_gray))

        v1.setBackgroundColor(resources.getColor(R.color.blue))
        v2.setBackgroundColor(resources.getColor(R.color.txt_gray))
        login_register_button.setText(getString(R.string.Login))
        pass_top.setText(getString(R.string.password))
        forgot_password.visibility = View.VISIBLE
        by_click.visibility = View.GONE
        terms.visibility = View.GONE
        b_registr = false

        email_ed.setText("")
        password_ed.setText("")
        confirm_password_ed.setText("")
    }

    private fun change_to_signup() {

        confirm_layout.visibility = View.VISIBLE
        change_login.setTextColor(resources.getColor(R.color.txt_gray))
        change_register.setTextColor(resources.getColor(R.color.blue))

        v2.setBackgroundColor(resources.getColor(R.color.blue))
        v1.setBackgroundColor(resources.getColor(R.color.txt_gray))
        login_register_button.setText(getString(R.string.register))
        pass_top.setText(getString(R.string.new_password))
        forgot_password.visibility = View.GONE
        by_click.visibility = View.VISIBLE
        terms.visibility = View.VISIBLE
        b_registr = true

        email_ed.setText("")
        password_ed.setText("")
        confirm_password_ed.setText("")

    }


    private fun showToast(s: String) {
        val toast = Toast.makeText(applicationContext, "" + s, Toast.LENGTH_LONG)
        toast.show()

    }


    fun login_fun(email: String, password: String) {
         var progressDialog = ProgressDialog(this)
         progressDialog.setCancelable(false)
         progressDialog.setMessage(resources.getString(R.string.please_wait))
         progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
         progressDialog.show()

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    Log.e("TAG", "signInWithEmail:success")
                    val user = mAuth!!.currentUser
                    user?.let { checkIfEmailVerified(it?.getUid()) }
                    progressDialog.cancel()
                } else {
                    // Log.w(TAG, "signInWithEmail:failure", task.getException().getLocalizedMessage());
                    showToast(task.exception!!.localizedMessage.toString())
                    progressDialog.cancel()
                }
            }
    }


    private fun checkIfEmailVerified(userid: String) {
        val user = FirebaseAuth.getInstance().currentUser!!
        if (user.isEmailVerified) {
           // showToast("Email Verified")
               val user_id = user.uid
            save_user(user_id)

        } else {
            showToast(resources.getString(R.string.email_not_verified))
            sendVerificationEmail()
        }
    }




    fun sighup_fun(email: String, password: String) {

        /*val intent = Intent(this, StartApp::class.java)
        startActivity(intent)*/

        var progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage(resources.getString(R.string.please_wait))
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.show()

        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth!!.currentUser
                    val uid = user!!.uid
                    /*  mDatabase.child(uid).child("Name").setValue(name)
                    startActivity(Intent(this, Timeline::class.java))*/
                    //  Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                    change_to_login()
                    sendVerificationEmail()
                } else {
                    // showToast(task.exception!!.message!!)
                    progressDialog.dismiss()
                  //  change_to_login()
                  //  sendVerificationEmail()
                }
            })
    }

    fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun validate(count: Int) {
        if (count > 0) {
            if (b_registr) {
                if (b_email && b_pass && b_confm_pass) {
                    login_register_button.setBackground(resources.getDrawable(R.drawable.btn_selected_background))
                } else {
                    login_register_button.setBackground(resources.getDrawable(R.drawable.btn_unselected_background))
                }
            } else {

                if (b_email && b_pass) {
                    login_register_button.setBackground(resources.getDrawable(R.drawable.btn_selected_background))
                } else {
                    login_register_button.setBackground(resources.getDrawable(R.drawable.btn_unselected_background))
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private fun sendVerificationEmail() {
        val user = FirebaseAuth.getInstance().currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.e("VerificationEmail-->", "Email_sent")
                    // email sent
                    // after email is sent just logout the user and finish this activity
                    showToast(resources.getString(R.string.registered_successfully))
                    FirebaseAuth.getInstance().signOut()
                } else {
                    // email not sent, so display message and restart the activity or do whatever you wish to do
                    //restart this activity
                    overridePendingTransition(0, 0)
                    // finish()
                    overridePendingTransition(0, 0)
                    startActivity(intent)
                }
            }
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun isOnline(context: Context): Boolean {
        var mConnected: Boolean
        try {
            Log.e("Constant_class", "Detect Connection")
            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            mConnected = (networkInfo != null && networkInfo.isAvailable
                    && networkInfo.isConnected)
        } catch (e: Exception) {
            mConnected = false
            e.printStackTrace()
        }
        Log.e("Constant_class", "mConnected = $mConnected")
        return mConnected
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    private fun save_user(user_id: String) {

        // Create a new user with a email
        val user = hashMapOf(
           constant.USER_EMAIL to s_email,

        )
        val sharedPref = SharedPref()
        sharedPref.save_value(this,constant.USER_EMAIL,s_email)

// Add a new document with a generated ID
        db.collection(constant.USERS).document(user_id)
            .set(user)
            .addOnSuccessListener { documentReference ->
                Log.d("addOnSuccessListener", "DocumentSnapshot added with ID:");

                sharedPref.save_bool_value(this,constant.USER_LOGIN,true)

                val intent = Intent(this, StartApp::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Log.w("addOnFailureListener", "Error adding document", e)
                showToast("Something went wrong pleaase try again")
            }
    }



    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}



