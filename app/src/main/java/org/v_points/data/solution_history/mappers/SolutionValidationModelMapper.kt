package org.v_points.data.solution_history.mappers

import org.v_points.data.solution_history.models.response.SolutionValidationResponse
import org.v_points.domain.solution_history.models.SolutionValidationModel
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.v_points.ui_common.util.orDefaultNotValidValue
import org.v_points.ui_common.util.percentOf

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