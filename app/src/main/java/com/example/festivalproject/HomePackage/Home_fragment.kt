package com.example.festivalproject.HomePackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.festivalproject.R
import kotlinx.android.synthetic.main.fragment_home_fragment.*

class Home_fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val transaction: FragmentTransaction = this.requireActivity().supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )

        home_User.setOnClickListener {
            transaction.replace(R.id.home_linearlayout, User_fragment())
                .addToBackStack(null)
                .commit()
        }


        //연극
        homefragment_A000.setOnClickListener {
            perforFragmentChanger(transaction, "A000")
        }
        //음악
        homefragment_B000.setOnClickListener {
            perforFragmentChanger(transaction, "B000")
        }
        //무용
        homefragment_C000.setOnClickListener {
            perforFragmentChanger(transaction, "C000")
        }
        //미술
        homefragment_D000.setOnClickListener {
            perforFragmentChanger(transaction, "D000")
        }
        //건축
        homefragment_E000.setOnClickListener {
            perforFragmentChanger(transaction, "E000")
        }
        //영상
        homefragment_G000.setOnClickListener {
            perforFragmentChanger(transaction, "G000")
        }
        //문학
        homefragment_H000.setOnClickListener {
            perforFragmentChanger(transaction, "H000")
        }
        //문화정책
        homefragment_I000.setOnClickListener {
            perforFragmentChanger(transaction, "I000")
        }
        //축제문화공간
        homefragment_J000.setOnClickListener {
            perforFragmentChanger(transaction, "J000")
        }
        //기타
        homefragment_L000.setOnClickListener {
            perforFragmentChanger(transaction, "L000")
        }
    }
}

fun perforFragmentChanger(transaction: FragmentTransaction, realm: String) {
    val perforFragment: Fragment = Perfor_fragment()
    val bundle: Bundle = Bundle()
    bundle.putString("realm", realm)
    perforFragment.arguments = bundle
    transaction.replace(R.id.home_linearlayout, perforFragment)
        .addToBackStack(null)
        .commit()
}