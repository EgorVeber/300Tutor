package org.threehundredtutor.common

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback
import org.threehundredtutor.R
import org.threehundredtutor.databinding.ViewSnackbarBinding

class SnackbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val image: ImageView
    private val binding: ViewSnackbarBinding

    init {
        View.inflate(context, R.layout.view_snackbar, this)
        binding = ViewSnackbarBinding.bind(this)
        this.image = binding.icSnackbar
        clipToPadding = false
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(image, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(image, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(image, View.SCALE_X, 1f, 0f)
        val scaleY = ObjectAnimator.ofFloat(image, View.SCALE_Y, 1f, 0f)
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
        binding.messageSnackbar.text = text
    }
}