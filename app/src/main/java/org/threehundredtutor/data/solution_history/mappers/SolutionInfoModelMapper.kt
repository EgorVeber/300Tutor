package org.threehundredtutor.data.solution_history.mappers

import org.threehundredtutor.data.solution_history.models.response.SolutionInfoResponse
import org.threehundredtutor.domain.solution_history.models.SolutionInfoModel
import org.threehundredtutor.ui_common.util.orFalse
import org.threehundredtutor.ui_common.util_class.DateFormatter.toFormatDateDefault

fun SolutionInfoResponse.toSolutionInfoModel(): SolutionInfoModel =
    SolutionInfoModel(
        finishedOnUtc = finishedOnUtc?.toFormatDateDefault().orEmpty(),
        id = id.orEmpty(),
        isFinished = isFinished.orFalse(),
        startedOnUtc = startedOnUtc?.toFormatDateDefault().orEmpty(),
        testId = testId.orEmpty(),
        testName = testName.orEmpty()
    )