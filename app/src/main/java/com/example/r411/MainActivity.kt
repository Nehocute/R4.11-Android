package com.example.r411

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.r411.ui.login.Login

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, Login.newInstance())
                .commitNow()
        }
        //TODO Backtrack not redirecting to login
    }
}