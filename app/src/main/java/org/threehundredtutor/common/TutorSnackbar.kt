package org.threehundredtutor.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import org.threehundredtutor.R
import org.threehundredtutor.common.extentions.findSuitableParent

class TutorSnackbar(
    parent: ViewGroup,
    content: SnackbarView,
    backgroundColor: Int,
    private val title: String,
    private val message: String,
    private val buttonClick: (() -> Unit)?
) : BaseTransientBottomBar<TutorSnackbar>(parent, content, content) {

    init {
        getView().setBackgroundColor(backgroundColor)
        getView().setPadding(0, 0, 0, 0)
        val button = getView().findViewById<MaterialButton>(R.id.snackbarButton)
        if (buttonClick != null) {
            button.setOnClickListener { buttonClick.invoke() }
        } else {
            button.visibility = View.INVISIBLE
        }

        content.setTitle(title)
        content.setMessage(message)
    }

    companion object {
        fun make(
            view: View,
            backgroundColor: Int,
            title: String,
            message: String,
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

            customView.setBackgroundColor(backgroundColor)
            return TutorSnackbar(parent, customView, backgroundColor, title, message, buttonClick)
        }
    }
}