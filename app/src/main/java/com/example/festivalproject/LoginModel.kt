package com.example.festivalproject

import android.util.Log

public class LoginModel {
    private val userProfile = UserProfile("NO_ID","NO_PASSWORD","NO_SEX","NO_PHONENUM","NO_EMAIL")

    fun getUserProfile():UserProfile{
        return userProfile
    }

    fun modifyUserProfile(userId:String, userPassword:String, userSex:String, userPhoneNum:String, userEmail:String){
        Log.d("model","전송받은 데이터 db에 저장")
        Log.d("model","Id 데이터 변화전"+userProfile.userId,)
        userProfile.apply {
            this.userId = userId
            this.userPassword = userPassword
            this.userSex = userSex
            this.userPhoneNum = userPhoneNum
            this.userEmail = userEmail
        }
        Log.d("model","Id 데이터 변화후"+userProfile.userId+userProfile.userPassword)
    }
}