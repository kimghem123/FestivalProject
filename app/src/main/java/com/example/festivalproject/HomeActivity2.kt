package com.example.festivalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        supportFragmentManager.beginTransaction().replace(R.id.home_linearlayout,Home_fragment()).commit()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}