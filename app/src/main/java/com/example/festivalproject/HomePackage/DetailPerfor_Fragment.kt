package com.example.festivalproject.HomePackage

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.festivalproject.GetRealm
import com.example.festivalproject.GetSeq
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_detail_perfor_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__contents_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__summary_.*
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
        val seq = arguments?.getString("detailCode") as String
        val glide: RequestManager = Glide.with(this.requireActivity())

        val summaryFragment: Fragment = DetailPerfor_Summary_Fragment()
        val contentsFragment: Fragment = DetailPerfor_Contents_Fragment()

        getInfo(this.requireActivity(), glide, summaryFragment, contentsFragment, seq)

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


        Log.d("arg", seq)
    }

    override fun onPause() {
        super.onPause()
        Log.d("tran", "pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("tran", "des")
    }
}

fun getInfo(
    activity: Activity,
    glide: RequestManager,
    summaryFragment: Fragment,
    contentsFragment: Fragment,
    seq: String
) {
    (activity.application as MasterApplication).service.getBySeq(
        activity.getString(R.string.serviceKey),
        seq
    ).enqueue(object : Callback<GetSeq> {
        override fun onResponse(call: Call<GetSeq>, response: Response<GetSeq>) {
            if (response.isSuccessful) {
                Log.d("seq", "" + response.body()!!.msgBody.perforInfo.area)
                val info = response.body()
                val date =
                    info!!.msgBody.perforInfo.startDate + " ~ " + info!!.msgBody.perforInfo.endDate
                activity.apply {
                    detail_title.setText(info!!.msgBody.perforInfo.title)
                    detail_date.setText(date)
                    detail_addr.setText(info.msgBody.perforInfo.placeAddr)
                    detail_price.setText(info.msgBody.perforInfo.price)
                    //detail_url.setText(info.msgBody.perforInfo.url)
                    //detail_phone.setText(info.msgBody.perforInfo.phone)
                    detail_phone.setOnClickListener {
                        Log.d("phone",""+info.msgBody.perforInfo.phone)
                    }
                    detail_contents.setText(info.msgBody.perforInfo.contents1)
                }
                glide.load(info.msgBody.perforInfo.imgUrl).into(activity.detail_image)
            }
        }

        override fun onFailure(call: Call<GetSeq>, t: Throwable) {
            Log.d("seq", t.toString())
        }
    })
}