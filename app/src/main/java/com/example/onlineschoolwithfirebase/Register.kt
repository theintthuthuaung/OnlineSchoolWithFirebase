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

class Register : AppCompatActivity() {

    private var email:EditText? = null
    private var pwd:EditText? = null
    private var registerBTN:Button? = null
    private var firebaseAuth:FirebaseAuth? = null
    private var user:FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBTN = findViewById(R.id.register)
        email = findViewById(R.id.email_register_et)
        pwd = findViewById(R.id.password_register_et)
        firebaseAuth = FirebaseAuth.getInstance()

        registerBTN?.setOnClickListener {
            registerNewUser()

        }
    }

    private fun registerNewUser() {
        val email_txt = email?.text.toString().trim()
        val pwd_txt = pwd?.text.toString().trim()

        if(TextUtils.isEmpty(email_txt ) && TextUtils.isEmpty(pwd_txt)){
            Toast.makeText(applicationContext , "This can't be empty!!" , Toast.LENGTH_SHORT).show()
        }
        else {
            firebaseAuth?.createUserWithEmailAndPassword(email_txt , pwd_txt)?.addOnCompleteListener(object:OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {

                    if(task.isSuccessful){

                        Toast.makeText(applicationContext , "Account Created." , Toast.LENGTH_SHORT).show()

                        val user:FirebaseUser = firebaseAuth!!.currentUser!!
                        user.sendEmailVerification().addOnCompleteListener(object : OnCompleteListener<Void> {
                            override fun onComplete(task: Task<Void>) {

                                if (task.isSuccessful){

                                    Toast.makeText(applicationContext , "Check your email to verify!" , Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@Register , Login::class.java))
                                }

                                else {

                                    val error = task.exception?.message
                                    Toast.makeText(applicationContext , "Error " + error , Toast.LENGTH_SHORT).show()
                                }
                            }

                        })
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