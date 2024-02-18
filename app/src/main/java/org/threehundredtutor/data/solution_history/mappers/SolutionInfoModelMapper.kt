package org.threehundredtutor.data.solution_history.mappers

import org.threehundredtutor.common.orFalse
import org.threehundredtutor.common.utils.DateFormatter.toFormatDateDefault
import org.threehundredtutor.data.solution_history.models.response.SolutionInfoResponse
import org.threehundredtutor.domain.solution_history.models.SolutionInfoModel

// TODO разобраться что не приходит
fun SolutionInfoResponse.toSolutionInfoModel(): SolutionInfoModel =
    SolutionInfoModel(
        finishedOnUtc = finishedOnUtc?.toFormatDateDefault().orEmpty(),
        id = id.orEmpty(),
        isFinished = isFinished.orFalse(),
        startedOnUtc = startedOnUtc?.toFormatDateDefault().orEmpty(),
        testId = testId.orEmpty(),
        testName = testName.orEmpty()
    )