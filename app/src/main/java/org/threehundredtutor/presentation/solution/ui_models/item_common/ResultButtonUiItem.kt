package org.threehundredtutor.presentation.solution.ui_models.item_common

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class ResultButtonUiItem(
    val answerValidationResultType: AnswerValidationResultType
) : SolutionUiItem