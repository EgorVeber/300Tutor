package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.TestSolutionQueryResponse
import org.v_points.domain.solution.models.solution_models.AnswerModel
import org.v_points.domain.solution.models.test_model.TestSolutionModel

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