package com.example.festivalproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginModel:LoginModel = LoginModel()


        login_loginBtn.setOnClickListener {
            val userId = login_id.text.toString()
            val userPassword = login_password.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val result = loginModel.loginCheck(userId,userPassword,this@LoginActivity)
                if(result) {
                    if(login_autoLogin.isChecked){
                        loginModel.autoLogin(userId,userPassword,this@LoginActivity)
                    }
                    startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                }
                else  Log.d("login","시ㅣㅣㅣㅣ")
            }
        }

        login_register.setOnClickListener {
            registerButtonClick(this)
        }

        login_findPassword.setOnClickListener {
            loginModel.deleteAll(this)
        }
    }
}

fun registerButtonClick(activity: Activity){
    val intent = Intent(activity,RegisterActivity::class.java)
    activity.startActivity(intent)
}
