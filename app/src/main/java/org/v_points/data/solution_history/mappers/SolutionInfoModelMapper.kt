package org.v_points.data.solution_history.mappers

import org.v_points.data.solution_history.models.response.SolutionInfoResponse
import org.v_points.domain.solution_history.models.SolutionInfoModel
import org.v_points.ui_common.util.orFalse
import org.v_points.ui_common.util_class.DateFormatter.toFormatDateDefault

fun SolutionInfoResponse.toSolutionInfoModel(): SolutionInfoModel =
    SolutionInfoModel(
        finishedOnUtc = finishedOnUtc?.toFormatDateDefault().orEmpty(),
        id = id.orEmpty(),
        isFinished = isFinished.orFalse(),
        startedOnUtc = startedOnUtc?.toFormatDateDefault().orEmpty(),
        testId = testId.orEmpty(),
        testName = testName.orEmpty()
    )