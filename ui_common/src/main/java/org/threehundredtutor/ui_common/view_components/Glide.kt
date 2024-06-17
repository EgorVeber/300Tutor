package org.threehundredtutor.ui_common.view_components

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.threehundredtutor.ui_common.UiCoreDrawable

// TODO добавить error картинки
fun ImageView.loadImageMedium(path: String) {
    Glide.with(context).load(path)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        //.placeholder(UiCoreDrawable.placeholder_question) // ЛОАДЕР
        .into(this)
}

fun ImageView.loadImageOriginal(path: String) {
    Glide.with(context).load(path)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
            //.placeholder(UiCoreDrawable.placeholder_question) // ЛОАДЕР
        .into(this)
}

fun ImageView.loadIcon(path: String) {
    Glide.with(context).load(path)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .placeholder(UiCoreDrawable.ic_teacher)
        .into(this)
}

fun ImageView.loadImage(path: String) {
    Glide.with(context).load(path)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .placeholder(UiCoreDrawable.ic_logo)
        .into(this)
}