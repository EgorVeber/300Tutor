package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.test_response.QuestionResponse
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.domain.solution.models.test_model.SelectRightAnswerOrAnswersModel
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.domain.solution.models.test_model.TypeAnswerWithErrorsModel
import org.threehundredtutor.domain.solution.models.test_model.TypeRightAnswerQuestionModel

fun QuestionResponse.toQuestionModel(): QuestionModel =
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
    )