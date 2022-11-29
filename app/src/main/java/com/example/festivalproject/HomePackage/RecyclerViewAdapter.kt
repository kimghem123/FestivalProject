package com.example.festivalproject.HomePackage

import android.app.Activity
import android.content.Context
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
import com.example.festivalproject.R
import kotlinx.android.synthetic.main.perfor_item_view.view.*

class RecyclerViewAdapter(
    val itemList: GetRealm,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val transaction: FragmentTransaction,
    val activity: Activity
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
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
                val detailCode = itemList.msgBody.perforList.get(position).seq
                Log.d("arg",""+position)
                Log.d("arg",""+detailCode)
                bundle.putString("detailCode", detailCode)
                /*val seqSp = activity.getSharedPreferences("fragment", Context.MODE_PRIVATE)
                val seqEditor = seqSp.edit()
                seqEditor.putString("seq",detailCode)
                seqEditor.commit()*/
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

