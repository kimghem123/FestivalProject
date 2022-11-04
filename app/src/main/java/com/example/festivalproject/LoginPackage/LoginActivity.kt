package com.example.festivalproject.LoginPackage

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.festivalproject.HomePackage.HomeActivity
import com.example.festivalproject.R
import com.example.festivalproject.RegisterPackage.RegisterActivity
import com.example.festivalproject.Room.UserProfile
import com.example.festivalproject.UserDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_loginBtn.setOnClickListener {
            val user = UserProfile()
            user.apply {
                userId = login_id.text.toString()
                userPassword = login_password.text.toString()
            }
            CoroutineScope(Dispatchers.IO).launch {
                val result = user.loginCheck(this@LoginActivity)
                if(result){
                    if (login_autoLogin.isChecked) {
                        user.autoLogin(true, this@LoginActivity)
                    }
                    else user.autoLogin(false, this@LoginActivity)
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }
                else Log.d("login", "fail")
            }
        }

        login_register.setOnClickListener {
            registerButtonClick(this)
        }

        login_findPassword.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                deleteAll(this@LoginActivity)
            }
        }
    }
}

suspend fun deleteAll(context: Context) {
    val db = UserDatabase.getInstance(context.applicationContext)
        db!!.userDao().deleteAll()
}

fun registerButtonClick(activity: Activity) {
    val intent = Intent(activity, RegisterActivity::class.java)
    activity.startActivity(intent)
}
