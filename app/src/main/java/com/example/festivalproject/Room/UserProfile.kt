package com.example.festivalproject.Room

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.festivalproject.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

data class UserProfile(
    var userId: String? = null,
    var userPassword: String? = null,
    var userNickName: String? = null,
    var userSex: String? = null,
    var userPhoneNum: String? = null,
    var userEmail: String? = null
) {
    fun getterNickName(): String? {
        return userNickName
    }

    fun signUpNewUser(
        context: Context
    ) {
        val userProfileEntity = UserProfileEntity(
            userId,
            userPassword,
            userNickName,
            userSex,
            userPhoneNum,
            userEmail
        )
        val db = UserDatabase.getInstance(context.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            db!!.userDao().insert(userProfileEntity)
        }
    }

    suspend fun loginCheck(context: Context): Boolean {
        val db = UserDatabase.getInstance(context.applicationContext)
        val checkPassword = db!!.userDao().login(userId).toString()
        if (userPassword.equals(checkPassword)) return true else return false
    }

    fun autoLogin(flag: Boolean, context: Context) {
        val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("userId", userId)
        editor.putBoolean("flag", flag)
        editor.commit()
    }
}


