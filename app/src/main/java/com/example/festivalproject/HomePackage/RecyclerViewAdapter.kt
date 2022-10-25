package com.example.festivalproject.HomePackage

import android.os.Bundle
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
import com.example.festivalproject.Festival
import com.example.festivalproject.R

class RecyclerViewAdapter(
    val itemList: Festival,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val transaction: FragmentTransaction
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

            itemView.setOnClickListener {
                //val position: Int = absoluteAdapterPosition
                transaction.replace(R.id.home_linearlayout, DetailPerfor_Fragment())
                    .addToBackStack(null)
                    .commit()
                //detailPerforFragmentChanger(transaction/*,itemList.msgBody.perforList.get(position).seq!!*/)
            }

        }

        private fun detailPerforFragmentChanger(transaction: FragmentTransaction) {
            /*val detailperforFragment: Fragment = DetailPerfor_Fragment()
            val bundle: Bundle = Bundle()
            bundle.putString("detailCode", detailCode)
            detailperforFragment.arguments = bundle*/
            transaction.add(R.id.perfor_main, DetailPerfor_Fragment())
                .addToBackStack(null)
                .commit()
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

