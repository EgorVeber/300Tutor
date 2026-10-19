package com.v_points.app.domain.solution.models.test_model

import com.v_points.app.domain.solution.models.solution_models.AnswerModel

data class TestSolutionModel(
    val questionModel: QuestionModel,
    val answerModel: AnswerModel,
    val isQuestionLikedByStudent: Boolean
)
