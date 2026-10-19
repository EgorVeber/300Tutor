package org.v_points.presentation.solution.ui_models.answer_erros

import org.v_points.domain.solution.models.solution_models.AnswerValidationResultType
import org.v_points.presentation.solution.ui_models.SolutionUiItem

data class AnswerWithErrorsResultUiItem(
    val questionId: String,
    val answer: String,
    val rightAnswer: String,
    val answerValidationResultType: AnswerValidationResultType,
    val pointString: String,
) : SolutionUiItem