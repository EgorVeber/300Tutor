package org.threehundredtutor.data.solution_history.mappers

import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.common.utils.DateFormatter.toFormatDateDefault
import org.threehundredtutor.data.solution_history.models.response.SolutionInfoResponse
import org.threehundredtutor.domain.solution_history.models.SolutionInfoModel

fun SolutionInfoResponse.toSolutionInfoModel(): SolutionInfoModel =
    SolutionInfoModel(
        finishedOnUtc = finishedOnUtc?.toFormatDateDefault().orEmpty(),
        id = id ?: throw BadRequestException(),
        isFinished = isFinished ?: throw BadRequestException(),
        startedOnUtc = startedOnUtc?.toFormatDateDefault().orEmpty(),
        testId = testId ?: throw BadRequestException(),
        testName = testName ?: throw BadRequestException()
    )