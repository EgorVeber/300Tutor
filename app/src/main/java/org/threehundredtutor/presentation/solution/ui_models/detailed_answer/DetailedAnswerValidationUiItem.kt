package org.threehundredtutor.presentation.solution.ui_models.detailed_answer

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class DetailedAnswerValidationUiItem(
    val inputPoint: String,
    val pointTotal: String,
    val questionId: String,
    val type: AnswerValidationResultType,
    val isValidated: Boolean,
    val pointsString: String,
) : SolutionUiItem

