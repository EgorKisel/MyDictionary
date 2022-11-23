package com.geekbrains.mydictionary.view.image

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader() {

    fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .circleCrop()
            .into(container)
    }
}