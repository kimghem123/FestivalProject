package com.example.festivalproject.FindUserPasswordPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.festivalproject.FindUserIdPackage.DetailFindUserIdFragment
import com.example.festivalproject.R
import com.example.festivalproject.UserDatabase
import kotlinx.android.synthetic.main.activity_find_user_id.*
import kotlinx.android.synthetic.main.activity_find_user_password.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class FindUserPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_user_password)
        findPass_FindButton.setOnClickListener {
            val findId = findPass_Id.text.toString()
            val findName = findPass_Name.text.toString()
            val findPhoneNum = findPass_PhoneNum.text.toString()
            val detailFindUserPasswordFragment: Fragment = DetailFindUserPasswordFragment()
            val activity = this@FindUserPasswordActivity
            CoroutineScope(Dispatchers.IO).launch {
                val db = UserDatabase.getInstance(activity.applicationContext)
                val findIdResult = db!!.userProfileDao().findUserId(findName, findPhoneNum)
                if(findIdResult == findId){
                    val bundle: Bundle = Bundle()
                    bundle.putString("FindUserId", findIdResult)
                    Log.d("change",""+findIdResult)
                    detailFindUserPasswordFragment.arguments = bundle
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.findPass_Linear, detailFindUserPasswordFragment).commit()
                }
                else{
                    runOnUiThread { Toast.makeText(applicationContext,"일치하는 정보가 없습니다", Toast.LENGTH_SHORT).show() }
                }

            }
        }
    }
}