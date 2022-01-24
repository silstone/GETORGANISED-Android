package com.getorganized.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.getorganized.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SelectionScreen : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 123
    var progressDialog: ProgressDialog? = null

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection_screen)

        val google = findViewById(R.id.google) as RelativeLayout
        val email = findViewById(R.id.email) as RelativeLayout

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        createRequest()
        google.setOnClickListener {
            googleLogin()
        }

        email.setOnClickListener {
            val intent = Intent(this, LoginRegister::class.java)
            startActivity(intent)

        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun createRequest() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("949095118986-khvciumh5kilnpusn0g9ssjhmokupa7v.apps.googleusercontent.com") // .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private fun googleLogin() {
       // throw RuntimeException("Test Crash") // Force a crash
        val signInIntent: Intent = mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {

        progressDialog = ProgressDialog(this@SelectionScreen)
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Please wait...")
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog!!.show()

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")

                    val user = mAuth!!.currentUser
                    Log.e("user_name--->", user!!.displayName!!)
                    Log.e("user_name--->", user!!.email!!)
                    Log.e("user_name--->", user!!.uid)
                    val account = GoogleSignIn.getLastSignedInAccount(
                        applicationContext
                    )
                    if (account != null) {
                        val name = account.displayName
                        val id = account.id
                        val id_token = account.idToken
                        val given_name = account.givenName
                        val email = account.email
                        val photo_url = account.photoUrl.toString() + "?type=large"
                        Log.e("name--->", name)
                        Log.e("email--->", email)
                        Log.e("photo_url--->", photo_url)
                      //  checkUserData(email, name)
                    }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                  //  updateUI(null)
                }
            }
    }

}