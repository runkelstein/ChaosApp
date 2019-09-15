package com.chaoscorp.chaosapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton

import android.content.Intent
import android.view.View
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException




class SignInActivity :  AppCompatActivity() {

    lateinit var googleSignInClient : GoogleSignInClient

    private object RequestCodes {
        val SignIn = 9001;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("683726251045-5j13qabg3ci39rjulvftq7cj7t5t90o0.apps.googleusercontent.com")
            .build()



        googleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById<SignInButton>(R.id.sign_in_button).setOnClickListener(::OnSignInClicked)

    }

    fun OnSignInClicked(v: View) {

        val signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RequestCodes.SignIn);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RequestCodes.SignIn) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>?) {
        try {
            val account = completedTask?.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("SignInActivity","signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }


    }


    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    private fun updateUI(account: GoogleSignInAccount?) {

        if (account==null) {
            println("login of failed!!!!!!##*+?´ß9!!")
        } else {
            println("login of user ${account.email} was sucessfull! Token is ${account.idToken}")
        }


    }

}