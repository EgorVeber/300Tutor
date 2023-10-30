package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel

fun TestSolutionQueryResponse.toTestSolutionGeneralModel(): TestSolutionGeneralModel =
    TestSolutionGeneralModel(
        solutionId = solutionId.orEmpty(),
        hasCuratorValidation = hasCuratorValidation.orFalse(),
        canCheckSingleQuestion = canCheckSingleQuestion.orFalse(),
        studentGroupId = studentGroupId.orEmpty(),
        studentId = studentId.orEmpty(),
        startedOnUtc = startedOnUtc.orEmpty(),
        finishedOnUtc = finishedOnUtc.orEmpty(),
        isFinished = isFinished.orFalse(),
        descriptionTest = testResponse?.name ?: EMPTY_STRING,
        nameTest = testResponse?.name ?: EMPTY_STRING,
        testSolutionModel = toTestSolutionModel()
    )