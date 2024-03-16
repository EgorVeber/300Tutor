package org.threehundredtutor.ui_common.view_components

import android.content.Context
import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.cardview.widget.CardView

fun EditText.trimText(): String = text.toString().trim()

fun ImageView.setTint(@AttrRes attrRes: Int) {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrRes, typedValue, true)
    imageTintList = ColorStateList.valueOf(typedValue.data)
}

fun CardView.applyBackground(@AttrRes attrRes: Int) {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrRes, typedValue, true)
    setCardBackgroundColor(typedValue.data)
}

fun View.getColorAttr(@AttrRes attrRes: Int, needResId: Boolean = true): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrRes, typedValue, true)
    return if (needResId) typedValue.resourceId else typedValue.data
}

fun View.hideKeyboard(): Boolean = try {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )
} catch (ignored: RuntimeException) {
    false
}
