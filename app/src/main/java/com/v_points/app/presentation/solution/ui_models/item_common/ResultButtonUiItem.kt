package com.v_points.app.presentation.solution.ui_models.item_common

import com.v_points.app.domain.solution.models.solution_models.AnswerValidationResultType
import com.v_points.app.presentation.solution.ui_models.SolutionUiItem

data class ResultButtonUiItem(
    val questionId: String,
    val answerValidationResultType: AnswerValidationResultType,
    val pointString: String
) : SolutionUiItem