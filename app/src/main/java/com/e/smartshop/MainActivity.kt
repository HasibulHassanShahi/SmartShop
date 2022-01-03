package com.e.smartshop

import Activities.ProductActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}