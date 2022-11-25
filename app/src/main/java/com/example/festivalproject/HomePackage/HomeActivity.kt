package com.example.festivalproject.HomePackage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

            supportFragmentManager.beginTransaction().replace(R.id.home_linearlayout, Home_fragment()).commit()

    }
   override fun onPause() {
        super.onPause()
    }
}