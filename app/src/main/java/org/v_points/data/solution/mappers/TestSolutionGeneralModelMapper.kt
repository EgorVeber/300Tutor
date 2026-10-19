package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.TestSolutionQueryDetailedResponse
import org.v_points.data.solution.models.TestSolutionQueryResponse
import org.v_points.domain.solution.models.TestSolutionGeneralModel
import org.v_points.domain.solution.models.solution_models.AnswerModel
import org.v_points.domain.solution.models.test_model.QuestionModel
import org.v_points.domain.solution.models.test_model.TestSolutionModel
import org.v_points.ui_common.EMPTY_STRING
import org.v_points.ui_common.util.orFalse

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