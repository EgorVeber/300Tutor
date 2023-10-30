package org.threehundredtutor.presentation.solution.models.answer

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.models.SolutionItem

data class ResultAnswerUiModel(
    val answer: String,
    val rightAnswer: String,
    val answerValidationResultType: AnswerValidationResultType,
) : SolutionItem