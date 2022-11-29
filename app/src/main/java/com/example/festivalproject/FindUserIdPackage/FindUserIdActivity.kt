package com.example.festivalproject.FindUserIdPackage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.festivalproject.R
import com.example.festivalproject.UserDatabase
import kotlinx.android.synthetic.main.activity_find_user_id.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FindUserIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_user_id)

        findId_FindButton.setOnClickListener {
            val findName = findId_Name.text.toString()
            val findPhoneNum = findId_PhoneNum.text.toString()
            Log.d("findId", "tes")
            val detailFindUserIdFragment: Fragment = DetailFindUserIdFragment()
            CoroutineScope(Dispatchers.IO).launch {
                val db = UserDatabase.getInstance(this@FindUserIdActivity.applicationContext)
                val findId = db!!.userProfileDao().findUserId(findName, findPhoneNum)
                val bundle: Bundle = Bundle()
                Log.d("findId", "" + findId)
                bundle.putString("FindUserId", findId)
                detailFindUserIdFragment.arguments = bundle
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(findId_Linear.id, detailFindUserIdFragment)
                    .commit()
            }

        }

    }
}
