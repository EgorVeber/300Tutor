package org.threehundredtutor.data.solution.models.answer_response

data class ResponseObject(
    var answerOrAnswers: String,
    var isChecked: Boolean,
    var pointsValidation: PointsValidation,
    var questionId: String,
    var questionVersionId: String,
    var resultType: String
)