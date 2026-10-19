package org.v_points.presentation.solution.adapter

import android.view.View
import com.google.android.material.button.MaterialButton
import org.v_points.core.UiCoreAttr
import org.v_points.core.UiCoreDrawable
import org.v_points.core.UiCoreStrings
import org.v_points.domain.solution.models.solution_models.AnswerValidationResultType
import org.v_points.presentation.solution.solution_factory.DividerType
import org.v_points.ui_common.view_components.getColorAttr

fun MaterialButton.bindResultType(type: AnswerValidationResultType) {
    when (type) {
        AnswerValidationResultType.NOT_CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.warning, false))
            text = context.getString(UiCoreStrings.incorrect_answer)
            setIconResource(UiCoreDrawable.ic_cansel)
        }

        AnswerValidationResultType.PARTIALLY_CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.needCheckQuestion, false))
            text = context.getString(UiCoreStrings.allmost_the_right_answer)
            setIconResource(UiCoreDrawable.ic_allmost_answer)
        }

        AnswerValidationResultType.CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.defaultGreen, false))
            text = context.getString(UiCoreStrings.correct_answer)
            setIconResource(UiCoreDrawable.ic_correct_answer)
        }

        AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.needCheckQuestion, false))
            text = context.getString(UiCoreStrings.answer_needs_to_be_checked)
            setIconResource(UiCoreDrawable.ic_hand)
        }

        else -> {}
    }
}

fun View.bindDividerType(dividerType: DividerType) {
    setBackgroundResource(
        when (dividerType) {
            DividerType.BACKGROUND -> getColorAttr(UiCoreAttr.background)
            DividerType.CONTENT_BACKGROUND -> getColorAttr(UiCoreAttr.contentBackground)
        }
    )
}