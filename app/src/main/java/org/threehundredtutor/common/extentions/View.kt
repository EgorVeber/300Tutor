package org.threehundredtutor.common.extentions

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.threehundredtutor.common.TutorSnackbar

fun View?.findSuitableParent(): ViewGroup? {
    var view = this
    var fallback: ViewGroup? = null
    do {
        if (view is CoordinatorLayout) {
            return view
        } else if (view is FrameLayout) {
            if (view.id == android.R.id.content) {
                return view
            } else {
                fallback = view
            }
        }
        if (view != null) {
            val parent = view.parent
            view = if (parent is View) parent else null
        }
    } while (view != null)
    return fallback
}

fun Fragment.showSnackbar(
    title: String,
    backgroundColor: Int? = null,
    description: String? = null,
    buttonText: String? = null,
    length: Int = Snackbar.LENGTH_LONG,
    buttonClick: (() -> Unit)? = null,
    imageResId: Int? = null,
) {
    TutorSnackbar.make(
        view = this.requireView(),
        backgroundColor = backgroundColor,
        title = title,
        message = description,
        imageResId = imageResId,
        buttonText = buttonText,
        length = length,
        buttonClick = buttonClick
    ).show()
}