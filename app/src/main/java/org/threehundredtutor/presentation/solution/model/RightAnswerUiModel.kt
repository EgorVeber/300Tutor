package org.threehundredtutor.presentation.solution.model

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType

data class RightAnswerUiModel(
    val answer: String,
    val rightAnswer: String,
    val answerValidationResultType: AnswerValidationResultType,
) : HtmlItem

