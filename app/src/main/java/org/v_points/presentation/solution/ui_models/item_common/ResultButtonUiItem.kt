package org.v_points.presentation.solution.ui_models.item_common

import org.v_points.domain.solution.models.solution_models.AnswerValidationResultType
import org.v_points.presentation.solution.ui_models.SolutionUiItem

data class ResultButtonUiItem(
    val questionId: String,
    val answerValidationResultType: AnswerValidationResultType,
    val pointString: String
) : SolutionUiItem