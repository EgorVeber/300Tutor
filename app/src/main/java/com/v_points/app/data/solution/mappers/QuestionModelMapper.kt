package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.test_response.QuestionResponse
import com.v_points.app.domain.solution.models.test_model.QuestionModel
import com.v_points.app.domain.solution.models.test_model.SelectRightAnswerOrAnswersModel
import com.v_points.app.domain.solution.models.test_model.TestQuestionType
import com.v_points.app.domain.solution.models.test_model.TypeAnswerWithErrorsModel
import com.v_points.app.domain.solution.models.test_model.TypeRightAnswerQuestionModel

fun QuestionResponse.toQuestionModel(questionNumber: Int): QuestionModel =
    QuestionModel(
        questionId = id.orEmpty(),
        title = title.orEmpty(),
        titleBodyMarkUp = titleBodyMarkUp.orEmpty(),
        helpBodyMarkUp = helpBodyMarkUp.orEmpty(),
        answerExplanationMarkUp = answerExplanationMarkUp.orEmpty(),
        versionId = versionId.orEmpty(),
        testQuestionType = TestQuestionType.getType(type.orEmpty()),
        selectRightAnswerOrAnswersModel = selectRightAnswerOrAnswersDataResponse?.toSelectRightAnswerOrAnswersModel()
            ?: SelectRightAnswerOrAnswersModel.EMPTY,
        typeRightAnswerQuestionModel = typeRightAnswerQuestionDataResponse?.toTypeRightAnswerQuestionModel()
            ?: TypeRightAnswerQuestionModel.EMPTY,
        typeAnswerWithErrorsModel = typeAnswerWithErrorsDataResponse?.toTypeAnswerWithErrorsModel()
            ?: TypeAnswerWithErrorsModel.EMPTY,
        questionNumber = questionNumber + 1
    )