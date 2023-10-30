package org.threehundredtutor.presentation.solution.ui_models.right_answer

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class RightAnswerResultUiItem(
    val answer: String,
    val rightAnswer: String,
    val answerValidationResultType: AnswerValidationResultType,
) : SolutionUiItem