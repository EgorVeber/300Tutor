package com.v_points.app.data.solution_history.mappers

import com.v_points.app.data.solution_history.models.response.SolutionItemResponse
import com.v_points.app.domain.solution_history.models.SolutionHistoryItemModel
import com.v_points.app.domain.solution_history.models.SolutionInfoModel
import com.v_points.app.domain.solution_history.models.SolutionValidationModel
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import com.v_points.app.ui_common.util.orFalse

fun SolutionItemResponse.toSolutionHistoryItemModel(): SolutionHistoryItemModel =
    SolutionHistoryItemModel(
        solutionInfoModel = solutionInfoResponse?.toSolutionInfoModel() ?: SolutionInfoModel.EMPTY,
        solutionValidationModel =
        if (solutionInfoResponse?.isFinished.orFalse()) {
            solutionValidationResponse?.toSolutionValidationModel() ?: SolutionValidationModel.EMPTY
        } else SolutionValidationModel.EMPTY,
        unreadMessagesCount = unreadMessagesCount ?: DEFAULT_NOT_VALID_VALUE_INT
    )