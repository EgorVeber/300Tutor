package org.threehundredtutor.ui_common.view_components

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.threehundredtutor.ui_common.UiCoreDrawable

private const val FORMAT_JPG = ".jpg"
private const val CORNER_RADIUS_25 = 25

//TutorAndroid-32
fun ImageView.loadImageMedium(id: String, staticUrl: String) {
    Glide.with(context).load("$staticUrl$id$FORMAT_JPG")
        .placeholder(UiCoreDrawable.banner_question)
        .into(this)
}

fun ImageView.loadImageOriginal(id: String, staticUrl: String) {
    Glide.with(context).load("$staticUrl$id$FORMAT_JPG")
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .placeholder(UiCoreDrawable.banner_question)
        .into(this)
}