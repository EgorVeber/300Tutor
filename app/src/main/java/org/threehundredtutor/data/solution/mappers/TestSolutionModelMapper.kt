package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.test_model.TestSolutionModel

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