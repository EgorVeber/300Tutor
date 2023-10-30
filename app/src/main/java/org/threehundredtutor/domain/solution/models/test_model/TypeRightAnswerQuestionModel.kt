package org.threehundredtutor.domain.solution.models.test_model

data class TypeRightAnswerQuestionModel(
    val caseInSensitive: Boolean,
    val rightAnswers: List<String> 
) {
    companion object {
        val EMPTY = TypeRightAnswerQuestionModel(
            caseInSensitive = false,
            rightAnswers = emptyList()
        )
    }
}
