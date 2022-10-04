package com.example.festivalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().replace(R.id.linearlayout, HomeFragment()).commit()
        bottomNavigationView.setOnItemSelectedListener{ item ->
            when(item.itemId){
                R.id.page_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.linearlayout, HomeFragment()).commit()
                }
                R.id.page_search -> {
                    supportFragmentManager.beginTransaction().replace(R.id.linearlayout, SearchFragment()).commit()
                }
                R.id.page_my -> {
                    supportFragmentManager.beginTransaction().replace(R.id.linearlayout, UserFragment()).commit()
                }
            }
            true
        }
    }

    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.page_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearlayout , HomeFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.page_search -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearlayout , SearchFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.page_my -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearlayout , UserFragment()).commitAllowingStateLoss()
                return true
            }
        }
        return false
    }*/
}

