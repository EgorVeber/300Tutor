package org.v_points.presentation.solution.ui_models.right_answer

import org.v_points.domain.solution.models.solution_models.AnswerValidationResultType
import org.v_points.presentation.solution.ui_models.SolutionUiItem

data class RightAnswerResultUiItem(
    val questionId: String,
    val answer: String,
    val rightAnswer: String,
    val answerValidationResultType: AnswerValidationResultType,
    val pointsString: String,
) : SolutionUiItem