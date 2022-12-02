package com.example.festivalproject.LoginPackage

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.festivalproject.FindUserIdPackage.FindUserIdActivity
import com.example.festivalproject.FindUserPasswordPackage.FindUserPasswordActivity
import com.example.festivalproject.HomePackage.HomeActivity
import com.example.festivalproject.R
import com.example.festivalproject.RegisterPackage.RegisterActivity
import com.example.festivalproject.UserProfile
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
                val result = loginCheck(user,this@LoginActivity)
                if(result){
                    if (login_autoLogin.isChecked) {
                        autoLogin(user,true, this@LoginActivity)
                    }
                    else autoLogin(user,false, this@LoginActivity)
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }
                else runOnUiThread { Toast.makeText(applicationContext,"로그인 정보가 일치하지 않습니다",Toast.LENGTH_SHORT).show() }
            }
        }

        login_register.setOnClickListener {
            registerButtonClick(this)
        }

        login_findId.setOnClickListener {
            startActivity(Intent(this@LoginActivity,FindUserIdActivity::class.java))
        }

        login_findPassword.setOnClickListener {
            /*CoroutineScope(Dispatchers.IO).launch {
                deleteAll(this@LoginActivity)
            }*/
            startActivity(Intent(this@LoginActivity,FindUserPasswordActivity::class.java))
        }

        login_dbDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                deleteAll(this@LoginActivity)
            }
        }
    }
}

suspend fun loginCheck(user:UserProfile,context: Context): Boolean {
    val db = UserDatabase.getInstance(context.applicationContext)
    val checkPassword = db!!.userProfileDao().login(user.getterUserId()).toString()
    if (user.getterPassword().equals(checkPassword)) return true else return false
}

fun autoLogin(user:UserProfile,flag: Boolean, context: Context) {
    val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
    val editor = sp.edit()
    editor.putString("userId", user.getterUserId())
    editor.putBoolean("flag", flag)
    editor.commit()
}

suspend fun deleteAll(context: Context) {
    val db = UserDatabase.getInstance(context.applicationContext)
        db!!.userProfileDao().deleteAll()
}

fun registerButtonClick(activity: Activity) {
    val intent = Intent(activity, RegisterActivity::class.java)
    activity.startActivity(intent)
}
