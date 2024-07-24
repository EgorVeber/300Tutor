package org.threehundredtutor.ui_common.view_components

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.threehundredtutor.ui_common.UiCoreDrawable

// TODO добавить error картинки
fun ImageView.loadImageMedium(path: String) {
    Glide.with(context).load(path)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        //.override(this.layoutParams.width)
        .placeholder(UiCoreDrawable.ic_loading) // ЛОАДЕР
        .into(this)
}

fun ImageView.loadImageOriginal(path: String) {
    Glide.with(context).load(path)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .placeholder(UiCoreDrawable.ic_loading) // ЛОАДЕР
        .into(this)
}

fun ImageView.loadIcon(path: String, @DrawableRes placeHolder: Int) {
    Glide.with(context).load(path)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .placeholder(placeHolder)
        .into(this)
}