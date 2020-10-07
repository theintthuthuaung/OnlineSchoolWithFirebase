package com.example.onlineschoolwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun singnUpBTN (view : View) {

        startActivity(Intent(this@MainActivity , Register::class.java))
    }

    public fun login (view : View) {

        startActivity(Intent(this@MainActivity , Login::class.java))
    }
}