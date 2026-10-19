package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.TestSolutionQueryDetailedResponse
import com.v_points.app.data.solution.models.TestSolutionQueryResponse
import com.v_points.app.domain.solution.models.TestSolutionGeneralModel
import com.v_points.app.domain.solution.models.solution_models.AnswerModel
import com.v_points.app.domain.solution.models.test_model.QuestionModel
import com.v_points.app.domain.solution.models.test_model.TestSolutionModel
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.util.orFalse

fun TestSolutionQueryResponse.toTestSolutionGeneralModel(): TestSolutionGeneralModel =
    TestSolutionGeneralModel(
        solutionId = solutionId.orEmpty(),
        hasCuratorValidation = hasCuratorValidation.orFalse(),
        canCheckSingleQuestion = canCheckSingleQuestion.orFalse(),
        studentGroupId = studentGroupId.orEmpty(),
        studentId = studentId.orEmpty(),
        startedOnUtc = startedOnUtc.orEmpty(),
        finishedOnUtc = finishedOnUtc.orEmpty(),
        isFinished = isFinished.orFalse(),
        descriptionTest = testResponse?.name ?: EMPTY_STRING,
        nameTest = testResponse?.name ?: EMPTY_STRING,
        testSolutionModel = toTestSolutionModel()
    )

fun TestSolutionQueryDetailedResponse.toTestSolutionGeneralModel(): TestSolutionGeneralModel =
    TestSolutionGeneralModel(
        solutionId = solutionId.orEmpty(),
        hasCuratorValidation = hasCuratorValidation.orFalse(),
        canCheckSingleQuestion = canCheckSingleQuestion.orFalse(),
        studentGroupId = studentGroupId.orEmpty(),
        studentId = studentId.orEmpty(),
        startedOnUtc = startedOnUtc.orEmpty(),
        finishedOnUtc = finishedOnUtc.orEmpty(),
        isFinished = isFinished.orFalse(),
        descriptionTest = testResponseDetailed?.description ?: EMPTY_STRING,
        nameTest = testResponseDetailed?.name ?: EMPTY_STRING,
        testSolutionModel = testResponseDetailed?.questionResponses?.mapIndexed { index, questionDetailedResponse ->
            TestSolutionModel(
                questionModel = questionDetailedResponse.question?.toQuestionModel(questionNumber = index)
                    ?: QuestionModel.EMPTY,
                answerModel = questionDetailedResponse.answerWithValidation?.toAnswerModel()
                    ?: AnswerModel.EMPTY,
                isQuestionLikedByStudent = questionDetailedResponse.isQuestionLikedByStudent.orFalse()
            )
        }.orEmpty()
    )