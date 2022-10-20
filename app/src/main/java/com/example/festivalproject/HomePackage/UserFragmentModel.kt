package com.example.festivalproject.HomePackage

import android.content.Context

class UserFragmentModel {
    fun logout(context: Context){
        val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove("userId")
        editor.remove("userPassword")
        editor.commit()
    }
}