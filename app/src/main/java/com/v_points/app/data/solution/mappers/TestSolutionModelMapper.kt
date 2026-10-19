package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.TestSolutionQueryResponse
import com.v_points.app.domain.solution.models.solution_models.AnswerModel
import com.v_points.app.domain.solution.models.test_model.TestSolutionModel

fun TestSolutionQueryResponse.toTestSolutionModel(): List<TestSolutionModel> =
    testResponse?.toTestModel()?.questionList?.map { questionModel ->
        TestSolutionModel(
            questionModel = questionModel,
            answerModel = solutionResponse?.toSolutionModel()?.answerModelList?.find { answerModel ->
                questionModel.questionId == answerModel.questionId
            } ?: AnswerModel.EMPTY,
            isQuestionLikedByStudent = false
        )
    } ?: emptyList()