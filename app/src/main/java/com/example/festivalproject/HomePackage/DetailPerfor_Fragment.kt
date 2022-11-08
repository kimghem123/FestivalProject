package com.example.festivalproject.HomePackage

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.festivalproject.GetSeq
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R
import com.example.festivalproject.Room.UserFavorite
import com.example.festivalproject.Room.UserFavoriteEntity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_detail_perfor_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__contents_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__summary_.*
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
        val perforDetailInfo = PerforDetailInfo()
        perforDetailInfo.seq = arguments?.getString("detailCode")
        val glide: RequestManager = Glide.with(this.requireActivity())

        getInfo(this.requireActivity(), glide, perforDetailInfo)
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
            val sp = this.requireActivity().getSharedPreferences("login_sp", Context.MODE_PRIVATE)
            val userId = sp.getString("userId", null)


            val db2 = UserFavorite.getInstance(this.requireActivity().applicationContext)
            CoroutineScope(Dispatchers.IO).launch {
                val list = db2!!.userFavoriteDao().getFavorite(userId)
                Log.d("tet",""+list)
                val list2 = list!!.plus(perforDetailInfo.getDetailSeq()!!)
                Log.d("tet", ""+list2)
                db2!!.userFavoriteDao().addFavorite(list2, userId)
            }

        }
        detail_favoriteTest.setOnClickListener {
            val sp = this.requireActivity().getSharedPreferences("login_sp", Context.MODE_PRIVATE)
            val userId = sp.getString("userId", null)
            val db2 = UserFavorite.getInstance(this.requireActivity().applicationContext)
            CoroutineScope(Dispatchers.IO).launch {
                val list = db2!!.userFavoriteDao().getFavorite(userId)
                Log.d("tet", "" + list)
            }
        }

        Log.d("arg", perforDetailInfo.getDetailSeq()!!)
    }
}

fun getInfo(
    activity: Activity,
    glide: RequestManager,
    perforDetailInfo: PerforDetailInfo
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
                    detail_date_info =
                        info!!.msgBody.perforInfo.startDate + " ~ " + info!!.msgBody.perforInfo.endDate
                    detail_title_info = info.msgBody.perforInfo.title!!.replace("&amp;lt;", "<")
                        .replace("&amp;gt;", ">")
                    detail_addr_info = info.msgBody.perforInfo.placeAddr
                    detail_price_info = info.msgBody.perforInfo.price
                    detail_phone_info = info.msgBody.perforInfo.phone
                    detail_contents_info = info.msgBody.perforInfo.contents1
                    detail_img_info = info.msgBody.perforInfo.imgUrl
                }
                activity.apply {
                    detail_title.setText(perforDetailInfo.getDetailTitleInfo())
                    detail_date.setText(perforDetailInfo.getDetailDateInfo())
                    detail_addr.setText(perforDetailInfo.getDetailAddrInfo())
                    detail_price.setText(perforDetailInfo.getDetailPriceInfo())
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