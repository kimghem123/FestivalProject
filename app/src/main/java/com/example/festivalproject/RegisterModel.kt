package com.example.festivalproject

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.*
import java.security.AccessControlContext
import kotlin.collections.ArrayList


public class RegisterModel {
    private val userProfile =
        UserProfile("NO_ID", "NO_PASSWORD", "NO_SEX", "NO_PHONENUM", "NO_EMAIL")

    fun signUpNewUser(
        user: UserProfileEntity,
        context: Context
    ) {
        val db = UserDatabase.getInstance(context.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            db!!.userDao().insert(user)
        }
    }

    /*fun checkUser(context:Context){
        val db = UserDatabase.getInstance(context.applicationContext)
        val list = ArrayList<UserProfileEntity>()
        CoroutineScope(Dispatchers.IO).launch{
            db!!.userDao().getAll().forEach {
                list.add(it)
            }
            list.forEach {
                Log.d("test1",""+it.userId)
            }
        }
    }*/

    fun checkUser(context: Context) {
        val db = UserDatabase.getInstance(context.applicationContext)
        var list2 = ArrayList<UserProfileEntity>()
        val checkUserCoroutine = CoroutineScope(Dispatchers.IO).launch {
            db!!.userDao().getAll().forEach {
                list2.add(it)
            }
        }
    }

    /*fun modifyUserProfile(
        userId: String,
        userPassword: String,
        userSex: String,
        userPhoneNum: String,
        userEmail: String
    ) {
        Log.d("model", "전송받은 데이터 db에 저장")
        Log.d("model", "Id 데이터 변화전" + userProfile.userId)
        userProfile.apply {
            this.userId = userId
            this.userPassword = userPassword
            this.userSex = userSex
            this.userPhoneNum = userPhoneNum
            this.userEmail = userEmail
        }
        Log.d("model", "Id 데이터 변화후" + userProfile.userId + userProfile.userPassword)
    }*/
}


