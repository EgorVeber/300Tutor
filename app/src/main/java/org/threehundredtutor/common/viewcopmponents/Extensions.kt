package org.threehundredtutor.common.viewcopmponents

import android.view.View
import androidx.annotation.AttrRes
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import org.threehundredtutor.R
import org.threehundredtutor.common.getColorAttr
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.solution_factory.DividerType

fun Fragment.applyWindowColor(
    @AttrRes statusBarColorAttr: Int,
    @AttrRes navigationBarColorAttr: Int? = null
) {
    requireActivity().window.apply {
        statusBarColor = getColorAttr(statusBarColorAttr, false)
        navigationBarColor = getColorAttr(navigationBarColorAttr ?: statusBarColorAttr, false)
    }
}

fun Fragment.dropWindowColor() {
    requireActivity().window.apply {
        statusBarColor = getColorAttr(R.attr.background, false)
        navigationBarColor = getColorAttr(R.attr.contentBackground, false)
    }
}

fun MaterialButton.bindResultType(type: AnswerValidationResultType) {
    when (type) {
        AnswerValidationResultType.NOT_CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(R.attr.warning, false))
            text = context.getString(R.string.incorrect_answer)
            setIconResource(R.drawable.ic_incorrect_answer)
        }

        AnswerValidationResultType.PARTIALLY_CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(R.attr.primary, false))
            text = context.getString(R.string.allmost_the_right_answer)
            setIconResource(R.drawable.ic_allmost_answer)
        }

        AnswerValidationResultType.CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(R.attr.defaultGreen, false))
            text = context.getString(R.string.correct_answer)
            setIconResource(R.drawable.ic_correct_answer)
        }

        AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF -> {
            setBackgroundColor(getColorAttr(R.attr.primary, false))
            text = context.getString(R.string.answer_needs_to_be_checked)
            setIconResource(R.drawable.ic_hand)
        }

        else -> {}
    }
}

fun View.bindDividerType(dividerType: DividerType) {
    setBackgroundResource(
        when (dividerType) {
            DividerType.BACKGROUND -> getColorAttr(R.attr.background)
            DividerType.CONTENT_BACKGROUND -> getColorAttr(R.attr.contentBackground)
        }
    )
}