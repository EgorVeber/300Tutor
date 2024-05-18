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
        .into(this)
}

//.diskCacheStrategy(DiskCacheStrategy.ALL)
//.diskCacheStrategy(DiskCacheStrategy.NONE)
//.diskCacheStrategy(DiskCacheStrategy.DATA)
//.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//.diskCacheStrategy(DiskCacheStrategy.RESOURCE)

//Glide 3.x и 4.x: DiskCacheStrategy.NONE ничего не кэширует
//Glide 4.x: DiskCacheStrategy.DATA, Glide 3.x: DiskCacheStrategy.SOURCE кэширует только исходное изображение в полном разрешении.
//Glide 4.x: DiskCacheStrategy.RESOURCE Glide 3.x: DiskCacheStrategy.RESULT кэширует только окончательное изображение после уменьшения разрешения (и, возможно, преобразований) (поведение Glide 3.x по умолчанию)
//Только для Glide 4.x: DiskCacheStrategy.AUTOMATIC разумно выбирает стратегию кэширования в зависимости от ресурса (поведение Glide 4.x по умолчанию).
//Glide 3.x и 4.x: DiskCacheStrategy.ALL кэширует все версии образа.

fun ImageView.loadImage(path: String) {
    if (path.length > 6) {
        Glide.with(context)
            .load(path)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(UiCoreDrawable.ic_logo)
            .error(UiCoreDrawable.banner_question)
            .into(this)
    } else {
        Glide.with(context)
            .load("https://kursbio.ru/FileCopies/Images/Medium/$path$FORMAT_JPG")
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(UiCoreDrawable.banner_question)
            .placeholder(UiCoreDrawable.ic_logo)
            .into(this)
    }
}

///data/user/0/{package_name}/cache/image_manager_disk_cache/64c0af382f0a4b41c5dd210a3e945283d91c93b1938ee546f00b9ded701a7e40.0

fun ImageView.loadImageOriginal(id: String, staticUrl: String) {
    Glide.with(context).load("$staticUrl$id$FORMAT_JPG")
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(UiCoreDrawable.banner_question)
        .into(this)
}