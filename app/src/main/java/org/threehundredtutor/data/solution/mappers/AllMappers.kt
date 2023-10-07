package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.data.solution.models.solution_response.AnswerResponse
import org.threehundredtutor.data.solution.models.solution_response.PointsValidationResponse
import org.threehundredtutor.data.solution.models.solution_response.SolutionResponse
import org.threehundredtutor.data.solution.models.test_response.AnswerXResponse
import org.threehundredtutor.data.solution.models.test_response.QuestionResponse
import org.threehundredtutor.data.solution.models.test_response.SelectRightAnswerOrAnswersDataResponse
import org.threehundredtutor.data.solution.models.test_response.TestResponse
import org.threehundredtutor.data.solution.models.test_response.TypeAnswerWithErrorsDataResponse
import org.threehundredtutor.data.solution.models.test_response.TypeRightAnswerQuestionDataResponse
import org.threehundredtutor.domain.solution.models.TestSolutionQueryModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.models.solution_models.PointsValidationModel
import org.threehundredtutor.domain.solution.models.solution_models.SolutionModel
import org.threehundredtutor.domain.solution.models.test_model.AnswerXModel
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.domain.solution.models.test_model.SelectRightAnswerOrAnswersDataModel
import org.threehundredtutor.domain.solution.models.test_model.TestModel
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.domain.solution.models.test_model.TypeAnswerWithErrorsDataModel
import org.threehundredtutor.domain.solution.models.test_model.TypeRightAnswerQuestionDataModel


fun AnswerXResponse.toAnswerXModel(): AnswerXModel =
    AnswerXModel(
        isRightAnswer = isRightAnswer.orFalse(),
        text = text.orEmpty(),
    )

fun SelectRightAnswerOrAnswersDataResponse.toSelectRightAnswerOrAnswersDataModel(): SelectRightAnswerOrAnswersDataModel =
    SelectRightAnswerOrAnswersDataModel(
        answers = answers?.map { it.toAnswerXModel() } ?: emptyList(),
        rightAnswersCount = rightAnswersCount.orDefaultNotValidValue(),
        selectRightAnswerTitle = selectRightAnswerTitle.orEmpty(),
    )

fun QuestionResponse.toQuestionModel(): QuestionModel =
    QuestionModel(
        id = id.orEmpty(),
        title = title.orEmpty(),
        titleBodyMarkUp = titleBodyMarkUp.orEmpty(),
        helpBodyMarkUp = helpBodyMarkUp.orEmpty(),
        answerExplanationMarkUp = answerExplanationMarkUp.orEmpty(),
        versionId = versionId.orEmpty(),
        testQuestionType = TestQuestionType.getType(type.orEmpty()),
        selectRightAnswerOrAnswersDataModel = selectRightAnswerOrAnswersDataResponse?.toSelectRightAnswerOrAnswersDataModel()
            ?: SelectRightAnswerOrAnswersDataModel.EMPTY,
        typeRightAnswerQuestionDataModel = typeRightAnswerQuestionDataResponse?.toTypeRightAnswerQuestionDataModel()
            ?: TypeRightAnswerQuestionDataModel.EMPTY,
        typeAnswerWithErrorsDataModel = typeAnswerWithErrorsDataResponse?.toTypeAnswerWithErrorsDataModel()
            ?: TypeAnswerWithErrorsDataModel.EMPTY,
    )

fun TypeRightAnswerQuestionDataResponse.toTypeRightAnswerQuestionDataModel(): TypeRightAnswerQuestionDataModel =
    TypeRightAnswerQuestionDataModel(
        caseInSensitive = caseInSensitive.orFalse(),
        rightAnswers = rightAnswers ?: emptyList()
    )

fun TypeAnswerWithErrorsDataResponse.toTypeAnswerWithErrorsDataModel(): TypeAnswerWithErrorsDataModel =
    TypeAnswerWithErrorsDataModel(
        rightAnswer = rightAnswer.orEmpty()
    )


fun TestResponse.toTestModel(): TestModel =
    TestModel(
        description = description.orEmpty(),
        name = name.orEmpty(),
        questionList = questionResponses?.map { it.toQuestionModel() } ?: emptyList(),
    )

fun TestSolutionQueryResponse.toTestSolutionQueryModel(): TestSolutionQueryModel =
    TestSolutionQueryModel(
        solutionId = solutionId.orEmpty(),
        hasCuratorValidation = hasCuratorValidation.orFalse(),
        canCheckSingleQuestion = canCheckSingleQuestion.orFalse(),
        studentGroupId = studentGroupId.orEmpty(),
        studentId = studentId.orEmpty(),
        startedOnUtc = startedOnUtc.orEmpty(),
        finishedOnUtc = finishedOnUtc.orEmpty(),
        isFinished = isFinished.orFalse(),
        solutionModel = solutionResponse?.toSolutionModel() ?: SolutionModel.EMPTY,
        testModel = testResponse?.toTestModel() ?: TestModel.EMPTY,
    )


fun SolutionResponse.toSolutionModel(): SolutionModel =
    SolutionModel(
        answerModelList = answerResponse?.map { it.toAnswerModel() } ?: emptyList()
    )

fun AnswerResponse.toAnswerModel(): AnswerModel =
    AnswerModel(
        answerOrAnswers = answerOrAnswers.orEmpty(),
        isChecked = isChecked.orFalse(),
        pointsValidationModel = pointsValidationResponse?.toPointsValidationModel()
            ?: PointsValidationModel.EMPTY,
        questionId = questionId.orEmpty(),
        questionVersionId = questionVersionId.orEmpty(),
        answerValidationResultType = AnswerValidationResultType.getType(resultType.orEmpty()),
    )

fun PointsValidationResponse.toPointsValidationModel(): PointsValidationModel =
    PointsValidationModel(
        answerPoints = answerPoints.orDefaultNotValidValue(),
        description = description.orEmpty(),
        isValidated = isValidated.orFalse(),
        questionTotalPoints = questionTotalPoints.orDefaultNotValidValue(),
    )