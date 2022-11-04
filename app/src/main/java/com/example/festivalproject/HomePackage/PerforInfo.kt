package com.example.festivalproject.HomePackage

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.festivalproject.GetRealm
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class PerforInfo(
    var realm:String? =null,
    var realmName:String? = null,
    var sortStdr: String? = null,
    var perfor_recycler: RecyclerView? = null,
    var perfor_nodata: LinearLayout? = null,
    var sido: String? = null

) {
    fun getRealmInfo():String?{
        return realm
    }
    fun getRealmNameInfo():String?{
        return realmName
    }
    fun getSortArea(activity:Activity):String?{
        val sp = activity.getSharedPreferences("sortArea", Context.MODE_PRIVATE)
        val sortArea = sp.getString("sortArea", null)
        return sortArea
    }
    fun getSortName(activity: Activity):String?{
        val sp2 = activity.getSharedPreferences("sortName", Context.MODE_PRIVATE)
        val sortName = sp2.getString("sortName",null)
        return sortName
    }
    fun setSortArea(activity: Activity,text:String){
        val sp = activity.getSharedPreferences("sortArea", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("sortArea", text)
        editor.commit()
    }
    fun setSortName(activity: Activity){
        val sp2 = activity.getSharedPreferences("sortName", Context.MODE_PRIVATE)
        val editor = sp2.edit()
        editor.putString("sortName","true")
        editor.commit()
    }
    fun removeSortName(activity: Activity){
        val sp2 = activity.getSharedPreferences("sortName", Context.MODE_PRIVATE)
        val editor = sp2.edit()
        editor.remove("sortName")
        editor.commit()
    }
}