package org.threehundredtutor.ui_core

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class TutorSnackbar(
    parent: ViewGroup,
    content: SnackbarView
) : BaseTransientBottomBar<TutorSnackbar>(parent, content, content) {

    init {
        getView().setBackgroundColor(view.getColorAttr(R.attr.defaultTransparent, false))
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {
        fun make(
            view: View,
            @AttrRes backgroundColor: Int?,
            title: String,
            message: String?,
            imageResId: Int? = null,
            buttonText: String? = null,
            length: Int = Snackbar.LENGTH_LONG,
            buttonClick: (() -> Unit)? = null
        ): TutorSnackbar {
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val customView = (LayoutInflater.from(view.context).inflate(
                R.layout.layout_snackbar_tutor,
                parent,
                false
            ) as SnackbarView)

            backgroundColor?.let {
                customView.rootView.backgroundTintList =
                    ColorStateList.valueOf(view.getColorAttr(backgroundColor, false))
            }

            val tutorSnackbar = TutorSnackbar(parent, customView)

            customView.setTitle(title)

            imageResId?.let {
                customView.findViewById<ImageView>(R.id.icSnackbar).setImageResource(it)
            }

            buttonText?.let { customView.setButton(it) }

            message?.let { customView.setMessage(it) }


            val button = customView.findViewById<MaterialButton>(R.id.snackbarButton)
            buttonClick?.let {
                button.setOnClickListener { buttonClick.invoke();tutorSnackbar.dismiss() }
                backgroundColor?.let {
                    button.backgroundTintList = ColorStateList.valueOf(backgroundColor)
                }
            }
            return tutorSnackbar.setDuration(length)
        }
    }
}

fun View.getColorAttr(@AttrRes attrRes: Int, needResId: Boolean = true): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrRes, typedValue, true)
    return if (needResId) typedValue.resourceId else typedValue.data
}

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