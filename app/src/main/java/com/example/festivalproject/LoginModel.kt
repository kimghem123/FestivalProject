package com.example.festivalproject

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.contentValuesOf
import kotlinx.coroutines.*

class LoginModel {

    lateinit var checkPassword: String

    suspend fun loginCheck(userId: String, userPassword: String, context: Context): Boolean {
        val db = UserDatabase.getInstance(context.applicationContext)
        checkPassword = db!!.userDao().login(userId).toString()
        if (userPassword.equals(checkPassword)) {
            return true
        } else return false
    }


    fun deleteAll(context: Context) {
        val db = UserDatabase.getInstance(context.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            db!!.userDao().deleteAll()
        }
    }

    fun autoLogin(userId: String,userPassword: String,context: Context){
        val sp = context.getSharedPreferences("login_sp",Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("userId",userId)
        editor.putString("userPassword",userPassword)
        editor.commit()
    }
}



