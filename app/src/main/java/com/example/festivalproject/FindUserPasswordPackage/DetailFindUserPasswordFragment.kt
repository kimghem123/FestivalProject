package com.example.festivalproject.FindUserPasswordPackage

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.festivalproject.LoginPackage.LoginActivity
import com.example.festivalproject.R
import com.example.festivalproject.UserDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_detail_find_user_password.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailFindUserPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_find_user_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailFind_password2.doOnTextChanged { text, start, before, count ->
            if (detailFind_password1.text.toString() != detailFind_password2.text.toString()) {
                detailFind_passwordCheck.setText("#비밀번호가 일치하지 않습니다")
                detailFind_passwordCheck.setTextColor(Color.RED)
            } else if (detailFind_password1.text.toString() == detailFind_password2.text.toString()) {
                detailFind_passwordCheck.setText("비밀번호가 일치합니다")
                detailFind_passwordCheck.setTextColor(Color.BLUE)
            }
        }

        val findId = arguments?.getString("FindUserId")

        detailFind_changePass.setOnClickListener {
            val activity = this.requireActivity()
            val db = UserDatabase.getInstance(activity.applicationContext)
            CoroutineScope(Dispatchers.IO).launch {
                db!!.userProfileDao()
                    .changePassword(detailFind_password1.text.toString(), findId.toString())
                Log.d("change",""+detailFind_password1.text+findId)
                activity.startActivity(Intent(activity, LoginActivity::class.java))
                activity.finish()
            }
        }
    }
}