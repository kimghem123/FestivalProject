package com.example.festivalproject.FindUserIdPackage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.festivalproject.FindUserPasswordPackage.FindUserPasswordActivity
import com.example.festivalproject.LoginPackage.LoginActivity
import com.example.festivalproject.R
import kotlinx.android.synthetic.main.fragment_detail_find_user_id.*


class DetailFindUserIdFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_find_user_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val findId = arguments?.getString("FindUserId",null)

        if(findId != null)  detailFind_Id.setText(findId)
        else detailFind_Id.setText("일치하는 아이디가 없습니다")
        Log.d("findId", "" + findId)
        super.onViewCreated(view, savedInstanceState)

        detailFind_login.setOnClickListener {
            this.requireActivity().finish()
        }

        detailFind_findPass.setOnClickListener {
            this.requireActivity().startActivity(Intent(this.requireActivity(),FindUserPasswordActivity::class.java))
            this.requireActivity().finish()
        }
    }
}