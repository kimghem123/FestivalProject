package com.example.festivalproject

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class MasterApplication : Application(){
    val client = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    val retrofit1: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.culture.go.kr/openapi/rest/publicperformancedisplays/")
        .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
        .client(client)
        .build()

    val service = retrofit1.create(RetrofitService::class.java)
}