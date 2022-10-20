package com.example.festivalproject.HomePackage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.festivalproject.Festival
import com.example.festivalproject.MainActivity
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R
import kotlinx.android.synthetic.main.fragment_perfor_fragment.*
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Perfor_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfor_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val realm = arguments?.getString("realm") as String
        Log.d("test1", "" + realm)

        val activity = requireActivity()

        val glide: RequestManager = Glide.with(activity)

        (activity.application as MasterApplication).service.getByRealm(
            getString(R.string.serviceKey),
            "1",
            "1",
            realm,
            "1",
            "10",
            "20170101",
            "20231231"
        )
            .enqueue(object : Callback<Festival> {
                override fun onResponse(call: Call<Festival>, response: Response<Festival>) {
                    if (response.isSuccessful) {
                        val list = response.body()
                        val adapter =
                            RecyclerViewAdapter(list!!, LayoutInflater.from(activity),glide)
                        perfor_recycler.adapter = adapter
                        perfor_recycler.layoutManager = LinearLayoutManager(activity)
                    }
                }

                override fun onFailure(call: Call<Festival>, t: Throwable) {
                    Log.d("test1", t.toString())
                }
            })
    }

}

class RecyclerViewAdapter(
    val itemList: Festival,
    val inflater: LayoutInflater,
    val glide: RequestManager
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val place: TextView
        val date: TextView
        val image: ImageView

        init {
            title = itemView.findViewById(R.id.perfor_title)
            place = itemView.findViewById(R.id.perfor_place)
            date = itemView.findViewById(R.id.perfor_date)
            image = itemView.findViewById(R.id.perfor_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.perfor_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val decode = itemList.msgBody.perforList.get(position).title!!.replace("&amp;lt;", "<")
            .replace("&amp;gt;", ">")
        val startdate = itemList.msgBody.perforList.get(position).startDate
        val enddate = itemList.msgBody.perforList.get(position).endDate
        val date = startdate + " ~ " + enddate
        val place =
            itemList.msgBody.perforList.get(position).area + " , " + itemList.msgBody.perforList.get(
                position
            ).place
        holder.title.setText(decode)
        holder.place.setText(place)
        holder.date.setText(date)
        glide.load(itemList.msgBody.perforList.get(position).thumbnail)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return itemList.msgBody.perforList.size
    }
}