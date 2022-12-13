package com.example.festivalproject.HomePackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.festivalproject.R
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.fragment_detail_perfor_.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val img = intent.getStringExtra("imageUrl")
        val glide :RequestManager = Glide.with(this)
        glide.load(img).into(image_full)

        image_full.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}