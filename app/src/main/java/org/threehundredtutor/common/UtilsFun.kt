package org.threehundredtutor.common

import android.content.Context
import android.text.Spanned
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.core.text.HtmlCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.threehundredtutor.core.TutorApp

fun getAppContext(): Context = TutorApp.tutorAppInstance.applicationContext

fun Boolean?.orFalse() = this ?: false

fun Int?.orDefaultNotValidValue() = this ?: DEFAULT_NOT_VALID_VALUE_INT

// TODO привести к нормальному виду
const val staticDomain = "https://mini-apps.crocosoft.ru/FileCopies/Images/Medium/"
const val static = "https://mini-apps.crocosoft.ru/FileCopies/Images/Original/"


// TODO привести к нормальному виду
fun ImageView.loadServer(id: String) {
    Glide.with(context).load(staticDomain + id + ".jpg")
        .transform(MultiTransformation(RoundedCorners(25)))
        .into(this)
}

fun ImageView.loadCoin(id: String) {
    load(staticDomain + id + ".jpg") {
        crossfade(true)
        transformations(RoundedCornersTransformation(25f))
    }
}

fun View.getColorAttr(@AttrRes attrRes: Int, needResId: Boolean = true): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrRes, typedValue, true)
    return if (needResId) typedValue.resourceId else typedValue.data
}


fun String.fromHtml(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)



