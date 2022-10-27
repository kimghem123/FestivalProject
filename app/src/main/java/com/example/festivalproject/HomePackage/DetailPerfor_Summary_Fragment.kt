package com.example.festivalproject.HomePackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.festivalproject.R

open class DetailPerfor_Summary_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("tab","sum")
        return inflater.inflate(R.layout.fragment_detail_perfor__summary_, container, false)
    }
}