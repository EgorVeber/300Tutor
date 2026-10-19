package com.v_points.app.data.solution_history.mappers

import com.v_points.app.data.solution_history.models.response.SolutionInfoResponse
import com.v_points.app.domain.solution_history.models.SolutionInfoModel
import com.v_points.app.ui_common.util.orFalse
import com.v_points.app.ui_common.util_class.DateFormatter.toFormatDateDefault

fun SolutionInfoResponse.toSolutionInfoModel(): SolutionInfoModel =
    SolutionInfoModel(
        finishedOnUtc = finishedOnUtc?.toFormatDateDefault().orEmpty(),
        id = id.orEmpty(),
        isFinished = isFinished.orFalse(),
        startedOnUtc = startedOnUtc?.toFormatDateDefault().orEmpty(),
        testId = testId.orEmpty(),
        testName = testName.orEmpty()
    )