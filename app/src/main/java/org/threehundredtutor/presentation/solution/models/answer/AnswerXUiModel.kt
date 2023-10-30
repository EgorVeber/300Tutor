package org.threehundredtutor.presentation.solution.models.answer

import org.threehundredtutor.presentation.solution.models.SolutionItem

data class AnswerXUiModel(
    val questionId: String,
    val answer: String,
    val rightAnswer: Boolean,
    val checked: Boolean,
    val enabled: Boolean
) : SolutionItem