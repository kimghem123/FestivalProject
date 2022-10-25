package com.example.festivalproject.HomePackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.festivalproject.R

class DetailPerfor_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_perfor_, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("tran","tt")
    }

    override fun onPause() {
        super.onPause()
        Log.d("tran","pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("tran","des")
    }
}