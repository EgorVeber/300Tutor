package org.threehundredtutor.data.solution.models.answer_response

data class PointsValidation(
    var answerPoints: Int,
    var description: String,
    var isValidated: Boolean,
    var questionTotalPoints: Int
)