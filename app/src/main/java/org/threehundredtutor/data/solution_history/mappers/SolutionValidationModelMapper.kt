package org.threehundredtutor.data.solution_history.mappers

import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.common.percentOf
import org.threehundredtutor.data.solution_history.models.response.SolutionValidationResponse
import org.threehundredtutor.domain.solution_history.models.SolutionValidationModel

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