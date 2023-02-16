package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplash)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
        },1000)
    }
}