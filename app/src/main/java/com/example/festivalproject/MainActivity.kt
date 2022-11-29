package com.example.festivalproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.festivalproject.HomePackage.HomeActivity
import com.example.festivalproject.LoginPackage.LoginActivity
import com.facebook.stetho.Stetho

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        val userId = sp.getString("userId",null)
        val flag = sp.getBoolean("flag",false)
        if(userId != null&&flag == true){
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        }

    }
}