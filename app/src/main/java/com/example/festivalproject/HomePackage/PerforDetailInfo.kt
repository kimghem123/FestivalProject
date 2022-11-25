package com.example.festivalproject.HomePackage

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.example.festivalproject.GetSeq
import com.example.festivalproject.MasterApplication
import com.example.festivalproject.R
import kotlinx.android.synthetic.main.fragment_detail_perfor_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__contents_.*
import kotlinx.android.synthetic.main.fragment_detail_perfor__summary_.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class PerforDetailInfo(
    var detail_title_info: String? = null,
    var detail_date_info: String? = null,
    var detail_addr_info: String? = null,
    var detail_price_info: String? = null,
    var detail_url_info:String? = null,
    var detail_phone_info: String? = null,
    var detail_contents_info: String? = null,
    var detail_img_info: String? = null,
    var seq:String? = null
) {
    fun setDetailTitleInfo(detailTitleInfo:String){
        detail_title_info = detailTitleInfo
    }
    fun setDetailDateInfo(detailDataInfo:String){
        detail_date_info = detailDataInfo
    }
    fun setDetailAddrInfo(detailAddrInfo:String){
        detail_addr_info = detailAddrInfo
    }
    fun setDetailPriceInfo(detailPriceInfo:String){
        detail_price_info = detailPriceInfo
    }
    fun setDetailUrlInfo(detailUrlInfo:String){
        detail_url_info = detailUrlInfo
    }
    fun setDetailPhoneInfo(detailPhoneInfo:String){
        detail_phone_info = detailPhoneInfo
    }
    fun setDetailContentsInfo(detailContentsInfo:String){
        detail_contents_info = detailContentsInfo
    }
    fun setDetailImgInfo(detailImgInfo:String){
        detail_img_info = detailImgInfo
    }
    fun setDetailSeq(detailSeq:String){
        seq = detailSeq
    }

    fun getDetailTitleInfo():String?{
        return detail_title_info
    }
    fun getDetailDateInfo():String?{
        return detail_date_info
    }
    fun getDetailAddrInfo():String?{
        return detail_addr_info
    }
    fun getDetailPriceInfo():String?{
        return detail_price_info
    }
    fun getDetailUrlInfo():String?{
        return detail_url_info
    }
    fun getDetailPhoneInfo():String?{
        return detail_phone_info
    }
    fun getDetailContentsInfo():String?{
        return detail_contents_info
    }
    fun getDetailImgInfo():String?{
        return detail_img_info
    }
    fun getDetailSeq():String?{
        return seq
    }
}