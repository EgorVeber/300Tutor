package org.threehundredtutor.data.solution.mappers

import TestSolutionModel
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel

fun TestSolutionQueryResponse.toTestSolutionModel(): List<TestSolutionModel> =
    testResponse?.toTestModel()?.questionList?.map { questionModel ->
        TestSolutionModel(
            questionModel = questionModel,
            answerModel = solutionResponse?.toSolutionModel()?.answerModelList?.find { answerModel ->
                questionModel.questionId == answerModel.questionId
            } ?: AnswerModel.EMPTY
        )
    } ?: emptyList()