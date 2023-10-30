package org.threehundredtutor.presentation.solution.ui_models.answer_erros

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class AnswerWithErrorsResultUiItem(
    val answer: String,
    val rightAnswer: String,
    val answerValidationResultType: AnswerValidationResultType,
) : SolutionUiItem