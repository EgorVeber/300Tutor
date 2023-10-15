package org.threehundredtutor.domain.solution.models.test_model

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
)