package com.example.festivalproject.HomePackage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.festivalproject.GetRealm
import com.example.festivalproject.GetSeq
import com.example.festivalproject.R
import kotlinx.android.synthetic.main.perfor_item_view.view.*

class UserFavRecyclerViewAdapter(
    val getSeqList: MutableList<GetSeq?>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val transaction: FragmentTransaction
) : RecyclerView.Adapter<UserFavRecyclerViewAdapter.ViewHolder>() {

    fun addItem(getSeq:GetSeq?){
        getSeqList.add(getSeq)
        getSeqList.sortBy { it!!.msgBody.perforInfo.startDate }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val place: TextView
        val date: TextView
        val image: ImageView

        init {
            title = itemView.perfor_title
            place = itemView.perfor_place
            date = itemView.perfor_date
            image = itemView.perfor_image

            itemView.setOnClickListener {
                val position: Int = absoluteAdapterPosition
                val detailperforFragment: Fragment = DetailPerfor_Fragment()
                val bundle: Bundle = Bundle()
                val detailCode = getSeqList.get(position)!!.msgBody.perforInfo.seq
                bundle.putString("detailCode", detailCode)
                detailperforFragment.arguments = bundle
                transaction.replace(R.id.home_linearlayout, detailperforFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.perfor_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val decode = getSeqList.get(position)!!.msgBody.perforInfo.title!!.replace("&amp;lt;", "<").replace("&amp;gt;", ">")
        val startdate = getSeqList.get(position)!!.msgBody.perforInfo.startDate
        val enddate = getSeqList.get(position)!!.msgBody.perforInfo.endDate
        val date = startdate + " ~ " + enddate
        val place =
            getSeqList.get(position)!!.msgBody.perforInfo.area+" , "+getSeqList.get(position)!!.msgBody.perforInfo.place

        holder.title.setText(decode)
        holder.place.setText(place)
        holder.date.setText(date)
        glide.load(getSeqList.get(position)!!.msgBody.perforInfo.imgUrl)
            .apply(RequestOptions.bitmapTransform((RoundedCorners(20))))
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return getSeqList.size
    }
}

