package org.threehundredtutor.ui_core

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.snackbar.ContentViewCallback
import org.threehundredtutor.ui_core.databinding.PhotoItemBinding
import org.threehundredtutor.ui_core.databinding.ViewSnackbarBinding

class SnackbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val binding: ViewSnackbarBinding

    init {
        View.inflate(context, R.layout.view_snackbar, this)
        binding = ViewSnackbarBinding.bind(this)
    }

    override fun animateContentIn(delay: Int, duration: Int) {}

    override fun animateContentOut(delay: Int, duration: Int) {}

    fun setTitle(text: String) {
        binding.titleSnackbar.text = text
    }

    fun setMessage(text: String) {
        binding.messageSnackbar.isVisible = true
        binding.messageSnackbar.text = text
    }

    fun setButton(text: String) {
        binding.snackbarButton.visibility = VISIBLE
        binding.snackbarButton.text = text
    }
}