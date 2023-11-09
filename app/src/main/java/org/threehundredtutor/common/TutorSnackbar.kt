package org.threehundredtutor.common

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.RippleDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
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
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {
        fun make(
            view: View,
            backgroundColor: Int,
            title: String,
            message: String,
            imageResId: Int?,
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

            val imageView = customView.findViewById<ImageView>(R.id.icSnackbar)
            imageResId?.let {
                imageView.setImageResource(it)
            } ?: run {
                imageView.setImageResource(R.drawable.ic_info)
            }

            val button = customView.findViewById<MaterialButton>(R.id.snackbarButton)
            if (buttonClick != null) {
                val rippleDrawable = RippleDrawable(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            view.context,
                            android.R.color.transparent
                        )
                    ),
                    null,
                    null
                )
                button.background = rippleDrawable
                button.setOnClickListener {
                    buttonClick.invoke()
                    customView.visibility = View.GONE
                }
            }

            customView.setButton(buttonText)
            customView.setTitle(title)
            customView.setMessage(message)

            val attrs = intArrayOf(backgroundColor)
            val typedArray = customView.context.obtainStyledAttributes(attrs)

            val backgroundColor = typedArray.getColor(0, Color.TRANSPARENT)
            typedArray.recycle()

            customView.rootView.backgroundTintList =
                ColorStateList.valueOf(backgroundColor)
            val viewTutorSnackbar = TutorSnackbar(
                parent,
                customView
            )
            viewTutorSnackbar.view.elevation = 0f
            return viewTutorSnackbar.setDuration(length)
        }
    }
}