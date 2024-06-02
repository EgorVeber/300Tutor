package org.threehundredtutor.presentation.solution.adapter

import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
import org.threehundredtutor.core.UiCoreAttr
import org.threehundredtutor.core.UiCoreDrawable
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.solution_factory.DividerType
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel
import org.threehundredtutor.ui_common.view_components.getColorAttr
import org.threehundredtutor.ui_core.databinding.SolutionAnswerSelectRightItemBinding

fun MaterialButton.bindResultType(type: AnswerValidationResultType) {
    when (type) {
        AnswerValidationResultType.NOT_CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.warning, false))
            text = context.getString(UiCoreStrings.incorrect_answer)
            setIconResource(UiCoreDrawable.ic_incorrect_answer)
        }

        AnswerValidationResultType.PARTIALLY_CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.primary, false))
            text = context.getString(UiCoreStrings.allmost_the_right_answer)
            setIconResource(UiCoreDrawable.ic_allmost_answer)
        }

        AnswerValidationResultType.CORRECT_ANSWER -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.defaultGreen, false))
            text = context.getString(UiCoreStrings.correct_answer)
            setIconResource(UiCoreDrawable.ic_correct_answer)
        }

        // TODO добавить обработку  TutorAndroid-72 SolutionFragment Подправить UI.
        AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF -> {
            setBackgroundColor(getColorAttr(UiCoreAttr.primary, false))
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

fun AdapterDelegateViewBindingViewHolder<SelectRightAnswerUiModel, SolutionAnswerSelectRightItemBinding>.bindAll() {
    binding.checkbox.text = item.answer
    binding.checkbox.isChecked = item.checked
    binding.checkbox.isEnabled = item.enabled
    binding.iconContainer.isVisible = !item.enabled
    if (item.enabled) return
    if (item.rightAnswer) {
        binding.icon.setImageResource(UiCoreDrawable.ic_check)
        binding.iconContainer.setBackgroundResource(UiCoreDrawable.rectangle_full_green_background)
    } else {
        binding.icon.setImageResource(UiCoreDrawable.ic_incorrect_answer)
        binding.iconContainer.setBackgroundResource(UiCoreDrawable.rectangle_full_warning_background)
    }
}