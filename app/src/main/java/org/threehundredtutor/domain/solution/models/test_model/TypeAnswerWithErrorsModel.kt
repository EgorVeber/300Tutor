package org.threehundredtutor.domain.solution.models.test_model

import org.threehundredtutor.ui_common.EMPTY_STRING


data class TypeAnswerWithErrorsModel(
    val rightAnswer: String 
) {
    companion object {
        val EMPTY = TypeAnswerWithErrorsModel(
            rightAnswer = EMPTY_STRING
        )
    }
}