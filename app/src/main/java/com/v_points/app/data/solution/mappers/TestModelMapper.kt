package com.v_points.app.data.solution.mappers

import TestModel
import com.v_points.app.data.solution.models.test_response.TestResponse

fun TestResponse.toTestModel(): TestModel =
    TestModel(
        description = description.orEmpty(),
        name = name.orEmpty(),
        questionList = questionResponses?.mapIndexed { index, questionResponse ->
            questionResponse.toQuestionModel(
                questionNumber = index
            )
        }
            ?: emptyList(),
    )