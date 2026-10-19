package com.v_points.app.data.solution_history.mappers

import com.v_points.app.data.solution_history.models.response.SolutionValidationResponse
import com.v_points.app.domain.solution_history.models.SolutionValidationModel
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import com.v_points.app.ui_common.util.orDefaultNotValidValue
import com.v_points.app.ui_common.util.percentOf

fun SolutionValidationResponse.toSolutionValidationModel(): SolutionValidationModel =
    SolutionValidationModel(
        hasRightAnswerQuestionsCount = hasRightAnswerQuestionsCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        maxTotalPoints = maxTotalPoints ?: DEFAULT_NOT_VALID_VALUE_INT,
        maxTotalPointsString = maxTotalPoints.toString(),
        questionsCount = questionsCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        studentTotalPoints = studentTotalPoints ?: DEFAULT_NOT_VALID_VALUE_INT,
        studentTotalPointsString = studentTotalPoints.toString(),
        validatedQuestionsCount = validatedQuestionsCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        testIsSolvedPercent = maxTotalPoints?.percentOf(studentTotalPoints.orDefaultNotValidValue())
            .orDefaultNotValidValue(),
        issuesResolvedPercent = validatedQuestionsCount?.percentOf(hasRightAnswerQuestionsCount.orDefaultNotValidValue())
            .orDefaultNotValidValue()
    )