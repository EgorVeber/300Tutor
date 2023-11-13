package org.threehundredtutor.common

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import org.threehundredtutor.R
import org.threehundredtutor.common.extentions.findSuitableParent

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
            backgroundColor: Int?,
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

            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.layout_snackbar_tutor,
                parent,
                false
            ) as SnackbarView

            val tutorSnackbar = TutorSnackbar(parent, customView)


            customView.setTitle(title)

            imageResId?.let {
                customView.findViewById<ImageView>(R.id.icSnackbar).setImageResource(it)
            }

            buttonText?.let { customView.setButton(it) }

            message?.let { customView.setMessage(it) }

            backgroundColor?.let {
                customView.rootView.backgroundTintList = ColorStateList.valueOf(it)
            }

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