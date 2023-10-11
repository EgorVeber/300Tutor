package org.threehundredtutor.presentation.solution.model

import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType

data class SelectRightAnswerOrAnswersUiModel(
    val solutionId: String,
    val questionId: String,
    val testQuestionType: TestQuestionType,
    val selectRightAnswerTitle: String,
    val rightAnswersCount: Int,
    val answers: List<AnswerCheckedUiModel>,
    val answerValidationResultType: AnswerValidationResultType,
) : HtmlItem

