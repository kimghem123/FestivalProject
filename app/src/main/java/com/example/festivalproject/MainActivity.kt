package com.example.festivalproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.room.Room
import com.dd.processbutton.iml.ActionProcessButton
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindProgressButton(testregisterBtn)
        testregisterBtn.attachTextChangeAnimator()

        testregisterBtn.setOnClickListener {
            testregisterBtn.showProgress {
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
            }
            Handler(Looper.getMainLooper()).postDelayed({testregisterBtn.hideProgress(R.string.success)},2000)

            Log.d("aaa","aaa")


        }

    }
}