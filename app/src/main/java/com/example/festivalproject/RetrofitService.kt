package com.example.festivalproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("realm")
        fun getByRealm(
           @Query("serviceKey", encoded = true)
           serviceKey : String,
           @Query("place")
           place : String,
           @Query("sortStdr")
           sortStdr : String,
           @Query("realmCode")
           realmCode: String,
           @Query("cPage")
           cPage : String,
           @Query("rows")
           rows : String,
           @Query("from")
           from: String,
           @Query("to")
           to: String,
           @Query("sido")
           sido:String?
    ):Call<GetRealm>

        @GET("d/")
        fun getBySeq(
           @Query("serviceKey", encoded = true)
           serviceKey : String,
           @Query("seq")
           seq:String
        ):Call<GetSeq>
}