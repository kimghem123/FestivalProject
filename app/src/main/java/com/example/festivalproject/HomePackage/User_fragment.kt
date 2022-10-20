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
import kotlinx.android.synthetic.main.fragment_user_fragment3.*

class User_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_fragment3, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = this.activity as Activity
        user_logout.setOnClickListener {
            logout(context)
        }
    }
}

fun logout(context: Context){
    val userfragmentmodel:UserFragmentModel = UserFragmentModel()
    userfragmentmodel.logout(context)
    val intent = Intent(context,MainActivity::class.java)
    context.startActivity(intent)
}