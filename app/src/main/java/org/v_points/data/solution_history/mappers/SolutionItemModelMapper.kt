package org.v_points.data.solution_history.mappers

import org.v_points.data.solution_history.models.response.SolutionItemResponse
import org.v_points.domain.solution_history.models.SolutionHistoryItemModel
import org.v_points.domain.solution_history.models.SolutionInfoModel
import org.v_points.domain.solution_history.models.SolutionValidationModel
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.v_points.ui_common.util.orFalse

fun SolutionItemResponse.toSolutionHistoryItemModel(): SolutionHistoryItemModel =
    SolutionHistoryItemModel(
        solutionInfoModel = solutionInfoResponse?.toSolutionInfoModel() ?: SolutionInfoModel.EMPTY,
        solutionValidationModel =
        if (solutionInfoResponse?.isFinished.orFalse()) {
            solutionValidationResponse?.toSolutionValidationModel() ?: SolutionValidationModel.EMPTY
        } else SolutionValidationModel.EMPTY,
        unreadMessagesCount = unreadMessagesCount ?: DEFAULT_NOT_VALID_VALUE_INT
    )