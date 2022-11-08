package com.example.festivalproject.HomePackage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.festivalproject.MainActivity
import com.example.festivalproject.R
import com.example.festivalproject.UserProfile
import com.example.festivalproject.UserDatabase
import kotlinx.android.synthetic.main.fragment_user_fragment3.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class User_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_fragment3, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = this.requireActivity()

        val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val id = sp.getString("userId",null).toString()

        getUserNickName(id,context)

        user_logout.setOnClickListener {
            logout(context)
        }
    }
}

fun getUserNickName(
    id: String,
    context: Activity
) {
    val db = UserDatabase.getInstance(context.applicationContext)
    CoroutineScope(Dispatchers.IO).launch {
        var nickName = db!!.userProfileDao().getNickName(id)
        val userProfile = UserProfile()
        userProfile.userNickName = nickName
        context.user_nickName.setText(userProfile.getterNickName())
    }
}

fun logout(context: Activity){
    val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
    val editor = sp.edit()
    editor.remove("userId")
    editor.remove("userPassword")
    editor.commit()
    val intent = Intent(context,MainActivity::class.java)
    context.startActivity(intent)
}