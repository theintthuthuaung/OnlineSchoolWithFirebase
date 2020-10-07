package com.example.onlineschoolwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private var user_email:EditText? = null
    private var user_pwd:EditText? = null
    private var loginBTN:Button? = null
    private var firebaseAuth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_email = findViewById(R.id.email_login_et)
        user_pwd = findViewById(R.id.passord_login_et)
        loginBTN = findViewById(R.id.login)
        firebaseAuth = FirebaseAuth.getInstance()

        loginBTN?.setOnClickListener {

            loginToAccount()
        }
    }

    private fun loginToAccount() {

        var email = user_email?.text.toString().trim()
        var pwd = user_pwd?.text.toString().trim()

        if(TextUtils.isEmpty(email)  && TextUtils.isEmpty(pwd)) {

            Toast.makeText(applicationContext , "This fields can't be empty!" , Toast.LENGTH_SHORT).show()
        }

        else {

            firebaseAuth?.signInWithEmailAndPassword(email , pwd)?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {

                    if(task.isSuccessful){

                        Toast.makeText(applicationContext , "Login Successfull!" , Toast.LENGTH_SHORT).show()

                        val user: FirebaseUser = firebaseAuth!!.currentUser!!
                        if(user.isEmailVerified){

                            startActivity(Intent(this@Login , Dashboard::class.java))
                        }

                        else {

                            Toast.makeText(applicationContext , "Your Account is not verified!" , Toast.LENGTH_SHORT).show()
                        }
                    }

                    else {

                        val error = task.exception?.message
                        Toast.makeText(applicationContext , "Error " + error , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}