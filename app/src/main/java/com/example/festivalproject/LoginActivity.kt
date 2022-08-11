package com.example.festivalproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_loginBtn.setOnClickListener {
            loginButtonClick()
        }

        login_register.setOnClickListener {
            registerButtonClick(this)
        }
    }
}

fun loginButtonClick(){
    Log.d("test","test")
}

fun registerButtonClick(activity: Activity){
    val intent = Intent(activity,RegisterActivity::class.java)
    activity.startActivity(intent)
}