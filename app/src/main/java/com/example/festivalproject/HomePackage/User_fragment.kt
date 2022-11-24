package com.example.festivalproject.HomePackage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.festivalproject.*
import com.example.festivalproject.PerforInfo
import com.github.razir.progressbutton.hideProgress
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_detail_perfor_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__contents_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__summary_.*
import kotlinx.android.synthetic.main.fragment_user_fragment3.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class User_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = this.requireActivity()
        val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val id = sp.getString("userId", null).toString()
        val glide: RequestManager = Glide.with(context)
        val transaction: FragmentTransaction =
            this.requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

        getUserNickName(id, context)

        user_logout.setOnClickListener {
            logout(context)
        }
        var getSeqList = mutableListOf<GetSeq?>()
        val adapter = UserFavRecyclerViewAdapter(
            getSeqList,
            LayoutInflater.from(context),
            glide,
            transaction
        )
        context.user_recycler.adapter = adapter
        context.user_recycler.layoutManager = LinearLayoutManager(context)

        val db =
            UserFavDatabase.getInstance(this.requireActivity().applicationContext, gson = Gson())
        var list2 = mutableListOf<String>()
        CoroutineScope(Dispatchers.IO).async {
            val list = async {
                list2 =
                    Gson().fromJson(db!!.userFavDao().getfavList(id!!), Array<String>::class.java)
                        .toMutableList()
                list2
            }
            list.await().forEach {
                getInfo(context, it, adapter)
            }


        }

       /* val size = list2.size
        Log.d("tet", "" + list2)
        for (i in 0 until size) {
            Log.d("tet", "" + i)
            Log.d("tet", "" + list2.get(i))
            getInfo(context, list2.get(i), adapter)
        }*/

    }
}


fun getInfo(
    activity: Activity,
    seq: String,
    adapter: UserFavRecyclerViewAdapter
) {
    (activity.application as MasterApplication).service.getBySeq(
        activity.getString(R.string.serviceKey),
        seq
    ).enqueue(object : Callback<GetSeq> {
        override fun onResponse(call: Call<GetSeq>, response: Response<GetSeq>) {
            if (response.isSuccessful) {
                val info = response.body()
                adapter.addItem(info)
                //Log.d("tet",""+info!!.msgBody.perforInfo.place)
            }
        }

        override fun onFailure(call: Call<GetSeq>, t: Throwable) {
            Log.d("seq", t.toString())
        }
    })
}

fun getUserNickName(
    id: String,
    context: Activity
) {
    val db = UserDatabase.getInstance(context.applicationContext)
    CoroutineScope(Dispatchers.IO).launch {
        var nickName = db!!.userProfileDao().getNickName(id)
        val userProfile = UserProfile()
        userProfile.userNickName = nickName
        context.user_nickName.setText(userProfile.getterNickName())
    }
}

fun logout(context: Activity) {
    val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
    val editor = sp.edit()
    editor.remove("userId")
    editor.remove("userPassword")
    editor.commit()
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}