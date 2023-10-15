package org.threehundredtutor.data.solution.mappers

import TestModel
import org.threehundredtutor.data.solution.models.test_response.TestResponse

fun TestResponse.toTestModel(): TestModel =
    TestModel(
        description = description.orEmpty(),
        name = name.orEmpty(),
        questionList = questionResponses?.map { questionResponse -> questionResponse.toQuestionModel() }
            ?: emptyList(),
    )