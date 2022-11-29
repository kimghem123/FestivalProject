package com.example.festivalproject.HomePackage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.festivalproject.GetSeq
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R
import com.example.festivalproject.UserFavDatabase
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail_perfor_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor_.view.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__contents_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__contents_.view.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__summary_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__summary_.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPerfor_Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_perfor_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val seqSp = this.requireActivity().getSharedPreferences("fragment", Context.MODE_PRIVATE)
        val perforDetailInfo = PerforDetailInfo()
        perforDetailInfo.setDetailSeq(arguments?.getString("detailCode").toString())
        Log.d("tet", perforDetailInfo.getDetailSeq().toString())
        val glide: RequestManager = Glide.with(this.requireActivity())

        val sp = this.requireActivity().getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val userId = sp.getString("userId", null)
        val db =
            UserFavDatabase.getInstance(this.requireActivity().applicationContext, gson = Gson())
        val transaction = this.requireActivity().supportFragmentManager.beginTransaction()
        val activity = this.requireActivity()

        CoroutineScope(Dispatchers.Main).launch {
            val list =
                Gson().fromJson(db!!.userFavDao().getfavList(userId!!), Array<String>::class.java)
                    .toMutableList()
            if (list.contains(perforDetailInfo.getDetailSeq())) {
                detail_favorite.setBackgroundColor(Color.RED)
            } else {
                detail_favorite.setBackgroundColor(Color.GRAY)
            }
        }

        getInfo(activity, glide, perforDetailInfo, transaction)
        detail_tab.addTab(detail_tab.newTab().setText("개요"))
        detail_tab.addTab(detail_tab.newTab().setText("상세내용"))

        val pagerAdapter = DetailTabAdapter(this.requireActivity().supportFragmentManager, 2)
        detail_viewPager.adapter = pagerAdapter

        detail_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                detail_viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        detail_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(detail_tab))


        detail_favorite.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var list = db!!.userFavDao().getfavList(userId!!)
                var list2 = Gson().fromJson(list, Array<String>::class.java).toMutableList()
                if (list2.contains(perforDetailInfo.getDetailSeq())) {
                    list2.remove(perforDetailInfo.getDetailSeq())
                    detail_favorite.setBackgroundColor(Color.GRAY)
                } else {
                    list2.add(perforDetailInfo.getDetailSeq().toString())
                    detail_favorite.setBackgroundColor(Color.RED)
                }
                db.userFavDao().setfavList(list2, userId)
            }
        }
        detail_back.setOnClickListener {
            val fragmentManager = this.requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().remove(Perfor_fragment()).commit()
            fragmentManager.popBackStack()
        }
    }
}

fun getInfo(
    activity: Activity,
    glide: RequestManager,
    perforDetailInfo: PerforDetailInfo,
    transaction: FragmentTransaction
) {
    (activity.application as MasterApplication).service.getBySeq(
        activity.getString(R.string.serviceKey),
        perforDetailInfo.getDetailSeq()!!
    ).enqueue(object : Callback<GetSeq> {
        override fun onResponse(call: Call<GetSeq>, response: Response<GetSeq>) {
            if (response.isSuccessful) {
                Log.d("seq", "" + response.body()!!.msgBody.perforInfo.area)
                val info = response.body()

                perforDetailInfo.apply {
                    setDetailDateInfo(info!!.msgBody.perforInfo.startDate + " ~ " + info!!.msgBody.perforInfo.endDate)
                    setDetailTitleInfo(
                        info.msgBody.perforInfo.title!!.replace("&amp;lt;", "<")
                            .replace("&amp;gt;", ">")
                    )
                    setDetailAddrInfo(info.msgBody.perforInfo.placeAddr.toString())
                    setDetailPriceInfo(info.msgBody.perforInfo.price.toString())
                    setDetailContentsInfo(info.msgBody.perforInfo.contents1.toString())
                    setDetailUrlInfo(info.msgBody.perforInfo.url.toString())
                    setDetailPhoneInfo(info.msgBody.perforInfo.phone.toString())
                    setDetailImgInfo(info.msgBody.perforInfo.imgUrl.toString())
                }
                    activity.apply {
                        detail_title.text = perforDetailInfo.getDetailTitleInfo()
                        detail_date.text = perforDetailInfo.getDetailDateInfo()
                        detail_addr.text = perforDetailInfo.getDetailAddrInfo()
                        detail_price.text = perforDetailInfo.getDetailPriceInfo()
                        detail_url.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(perforDetailInfo.getDetailUrlInfo()))
                            startActivity(intent)
                        }
                        detail_phone.setOnClickListener {
                            Log.d("phone", "" + perforDetailInfo.getDetailPhoneInfo())
                        }
                        detail_contents.setText(perforDetailInfo.getDetailContentsInfo())
                    }
                glide.load(perforDetailInfo.getDetailImgInfo()).into(activity.detail_image)
            }
        }

        override fun onFailure(call: Call<GetSeq>, t: Throwable) {
            Log.d("seq", t.toString())
        }
    })
}