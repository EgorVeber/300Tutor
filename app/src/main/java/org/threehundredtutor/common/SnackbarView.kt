package org.threehundredtutor.common

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.snackbar.ContentViewCallback
import org.threehundredtutor.R
import org.threehundredtutor.databinding.ViewSnackbarBinding

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

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(binding.icSnackbar, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.icSnackbar, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(binding.icSnackbar, View.SCALE_X, 1f, 0f)
        val scaleY = ObjectAnimator.ofFloat(binding.icSnackbar, View.SCALE_Y, 1f, 0f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    fun setTitle(text: String) {
        binding.titleSnackbar.text = text
    }

    fun setMessage(text: String) {
        binding.messageSnackbar.isVisible = true
        binding.messageSnackbar.text = text
    }

    fun setButton(text: String?) {
        binding.snackbarButton.visibility = VISIBLE
        binding.snackbarButton.text = text
    }
}