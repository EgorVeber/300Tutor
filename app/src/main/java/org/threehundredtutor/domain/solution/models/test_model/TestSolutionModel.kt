package org.threehundredtutor.domain.solution.models.test_model

import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel

data class TestSolutionModel(
    val questionModel: QuestionModel,
    val answerModel: AnswerModel,
    val isQuestionLikedByStudent: Boolean
)

