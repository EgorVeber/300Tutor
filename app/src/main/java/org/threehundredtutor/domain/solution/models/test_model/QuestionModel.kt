package org.threehundredtutor.domain.solution.models.test_model

import org.threehundredtutor.common.EMPTY_STRING

data class QuestionModel(
    val questionId: String,
    val title: String,
    val titleBodyMarkUp: String,
    val helpBodyMarkUp: String,
    val answerExplanationMarkUp: String,
    val versionId: String,
    val testQuestionType: TestQuestionType,
    val selectRightAnswerOrAnswersModel: SelectRightAnswerOrAnswersModel,
    val typeRightAnswerQuestionModel: TypeRightAnswerQuestionModel,
    val typeAnswerWithErrorsModel: TypeAnswerWithErrorsModel,
) {
    companion object {

        val EMPTY = QuestionModel(
            questionId = EMPTY_STRING,
            title = EMPTY_STRING,
            titleBodyMarkUp = EMPTY_STRING,
            helpBodyMarkUp = EMPTY_STRING,
            answerExplanationMarkUp = EMPTY_STRING,
            versionId = EMPTY_STRING,
            testQuestionType = TestQuestionType.UNKNOWN,
            selectRightAnswerOrAnswersModel = SelectRightAnswerOrAnswersModel.EMPTY,
            typeRightAnswerQuestionModel = TypeRightAnswerQuestionModel.EMPTY,
            typeAnswerWithErrorsModel = TypeAnswerWithErrorsModel.EMPTY,
        )
    }
}
