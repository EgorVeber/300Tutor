package org.threehundredtutor.common

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import org.threehundredtutor.R
import org.threehundredtutor.common.extentions.findSuitableParent

class TutorSnackbar(
    parent: ViewGroup,
    content: SnackbarView,
    title: String,
    message: String,
    buttonText: String?,
    private val buttonClick: (() -> Unit)?
) : BaseTransientBottomBar<TutorSnackbar>(parent, content, content) {

    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
        val button = getView().findViewById<MaterialButton>(R.id.snackbarButton)
        if (buttonClick != null) {
            button.setOnClickListener {
                buttonClick.invoke()
                getView().visibility = View.GONE
            }
        } else {
            button.visibility = View.GONE
        }

        content.setButton(buttonText)
        content.setTitle(title)
        content.setMessage(message)
    }

    companion object {
        fun make(
            view: View,
            backgroundColor: Int,
            title: String,
            message: String,
            buttonText: String? = null,
            length: Int?,
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

            // Получаем атрибуты
            val attrs = intArrayOf(backgroundColor)
            val typedArray = view.context.obtainStyledAttributes(attrs)

            // Извлекаем цвет из атрибутов
            val backgroundColor = typedArray.getColor(0, Color.TRANSPARENT)
            typedArray.recycle()

            customView.rootView.backgroundTintList =
                ColorStateList.valueOf(backgroundColor)
            val viewTutorSnackbar = TutorSnackbar(parent, customView, title, message, buttonText, buttonClick)
            viewTutorSnackbar.view.elevation = 0f
            return viewTutorSnackbar.setDuration(length!!)
        }
    }
}