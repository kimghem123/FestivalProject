package com.example.festivalproject.HomePackage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DetailTabAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return DetailPerfor_Summary_Fragment()
            }
            1 -> {
                return DetailPerfor_Contents_Fragment()
            }
            else -> {
                return DetailPerfor_Summary_Fragment()
            }
        }
    }
}