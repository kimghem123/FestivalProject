package com.example.festivalproject.HomePackage

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.festivalproject.GetRealm
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R
import com.orhanobut.dialogplus.DialogPlus
import kotlinx.android.synthetic.main.fragment_perfor_fragment.*
import kotlinx.android.synthetic.main.sort_area_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Perfor_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfor_fragment, container, false)
        Log.d("life", "create")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("life", "created")
        super.onViewCreated(view, savedInstanceState)

        val list = arrayOf<String>("전체", "서울", "경기", "인천", "대구", "광주", "대전", "울산")
        val activity = requireActivity()

        val perforListInfo = PerforListInfo()
        perforListInfo.setterRealm(arguments?.getString("realm") as String)
        perforListInfo.setterRealmName(arguments?.getString("realmName") as String)

        val adapter = DialogAdapter(activity, list)

        val glide: RequestManager = Glide.with(activity)

        val transaction: FragmentTransaction =
            this.requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

        perfor_title.setText(perforListInfo.getRealmNameInfo())

        if (perforListInfo.getSortArea(activity) == "전체"&&perforListInfo.getSortName(activity).equals(null))
            getList(activity, perforListInfo.getRealmInfo()!!, "1", perfor_recycler, perfor_nodata, glide, null, transaction)
        else if(perforListInfo.getSortArea(activity) == "전체"&&perforListInfo.getSortName(activity).equals("true")){
            getList(
                activity,
                perforListInfo.getRealmInfo()!!,
                "2",
                perfor_recycler,
                perfor_nodata,
                glide,
                null,
                transaction
            )
        }
        else if (perforListInfo.getSortArea(activity) != "전체"&&perforListInfo.getSortName(activity).equals(null)){
            getList(
                activity,
                perforListInfo.getRealmInfo()!!,
                "1",
                perfor_recycler,
                perfor_nodata,
                glide,
                perforListInfo.getSortArea(activity),
                transaction
            )
        }
        else if (perforListInfo.getSortArea(activity) != "전체"&&perforListInfo.getSortName(activity).equals("true")){
            getList(
                activity,
                perforListInfo.getRealmInfo()!!,
                "2",
                perfor_recycler,
                perfor_nodata,
                glide,
                perforListInfo.getSortArea(activity),
                transaction
            )
        }

        perfor_sort_byPlace.setOnClickListener {
            val dialog = DialogPlus.newDialog(activity)
                .setAdapter(adapter)
                .setOnItemClickListener { dialog, item, view, position ->
                    val textView = view.sort_area_name
                    perforListInfo.setSortArea(activity,textView.text.toString())
                    perforListInfo.removeSortName(activity)
                    dialog.dismiss()
                    if (textView.text.toString().equals("전체")) {
                        getList(
                            activity,
                            perforListInfo.getRealmInfo()!!,
                            "1",
                            perfor_recycler,
                            perfor_nodata,
                            glide,
                            null,
                            transaction
                        )
                    } else {
                        getList(
                            activity,
                            perforListInfo.getRealmInfo()!!,
                            "1",
                            perfor_recycler,
                            perfor_nodata,
                            glide,
                            textView.text.toString(),
                            transaction
                        )
                    }
                }
                .setExpanded(true)
                .setCancelable(true)
                .setHeader(R.layout.sor_area_header)
                .create()
            dialog.show()
        }
        perfor_sort_byName.setOnClickListener {
            perforListInfo.setSortName(activity)
            if (perforListInfo.getSortArea(activity).equals("전체")) {
                getList(
                    activity,
                    perforListInfo.getRealmInfo()!!,
                    "2",
                    perfor_recycler,
                    perfor_nodata,
                    glide,
                    null,
                    transaction
                )
                Log.d("name", ""+perforListInfo.getSortArea(activity))
            } else {
                getList(
                    activity,
                    perforListInfo.getRealmInfo()!!,
                    "2",
                    perfor_recycler,
                    perfor_nodata,
                    glide,
                    perforListInfo.getSortArea(activity),
                    transaction
                )
                Log.d("name", "" + perforListInfo.getSortArea(activity))
            }
        }
        perfor_sort_byDate.setOnClickListener {
            perforListInfo.removeSortName(activity)
            if (perforListInfo.getSortArea(activity) == "전체")
                getList(activity, perforListInfo.getRealmInfo()!!, "1", perfor_recycler, perfor_nodata, glide, null, transaction)
            else if (perforListInfo.getSortArea(activity) != "전체"){
                getList(
                    activity,
                    perforListInfo.getRealmInfo()!!,
                    "1",
                    perfor_recycler,
                    perfor_nodata,
                    glide,
                    perforListInfo.getSortArea(activity),
                    transaction
                )
            }
        }
        perfor_back.setOnClickListener {
            val fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction().remove(Perfor_fragment()).commit()
            fragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        val perforListInfo = PerforListInfo()
        perforListInfo.removeSortName(this.requireActivity())
        super.onDestroy()
    }

}


fun getList(
    activity: Activity,
    realm: String,
    sortStdr: String,
    perfor_recycler: RecyclerView,
    perfor_nodata: LinearLayout,
    glide: RequestManager,
    sido: String? = null,
    transaction: FragmentTransaction
) {
    (activity.application as MasterApplication).service.getByRealm(
        activity.getString(R.string.serviceKey),
        "1",
        sortStdr,
        realm,
        "1",
        "10",
        "20170101",
        "20181231",
        sido
    )
        .enqueue(object : Callback<GetRealm> {
            override fun onResponse(call: Call<GetRealm>, response: Response<GetRealm>) {
                if (response.isSuccessful) {
                    perfor_recycler.isVisible = true
                    perfor_nodata.isGone = true
                    val list = response.body()
                    val adapter =
                        RecyclerViewAdapter(
                            list!!,
                            LayoutInflater.from(activity),
                            glide,
                            transaction
                        )
                    perfor_recycler.adapter = adapter
                    perfor_recycler.layoutManager = LinearLayoutManager(activity)
                }
            }

            override fun onFailure(call: Call<GetRealm>, t: Throwable) {
                perfor_recycler.isGone = true
                perfor_nodata.isVisible = true
                Log.d("test1", t.toString())
            }
        })
}

