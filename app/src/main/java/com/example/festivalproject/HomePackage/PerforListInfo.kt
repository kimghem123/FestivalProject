package com.example.festivalproject.HomePackage

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

data class PerforListInfo(
    var realmCode:String? =null,
    var realmName:String? = null,
    var sortStdr: String? = null,
    var perfor_recycler: RecyclerView? = null,
    var perfor_nodata: LinearLayout? = null,
    var sido: String? = null

) {
    fun setterRealm(realm:String){
        this.realmCode = realm
    }

    fun setterRealmName(realmName:String){
        this.realmName = realmName
    }

    fun getRealmInfo():String?{
        return realmCode
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