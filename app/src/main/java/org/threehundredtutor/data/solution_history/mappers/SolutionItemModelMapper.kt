package org.threehundredtutor.data.solution_history.mappers

import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution_history.models.response.SolutionItemResponse
import org.threehundredtutor.domain.solution_history.models.SolutionHistoryItemModel
import org.threehundredtutor.domain.solution_history.models.SolutionInfoModel
import org.threehundredtutor.domain.solution_history.models.SolutionValidationModel

fun SolutionItemResponse.toSolutionHistoryItemModel(): SolutionHistoryItemModel =
    SolutionHistoryItemModel(
        solutionInfoModel = solutionInfoResponse?.toSolutionInfoModel() ?: SolutionInfoModel.EMPTY,
        solutionValidationModel =
        if (solutionInfoResponse?.isFinished.orFalse()) {
            solutionValidationResponse?.toSolutionValidationModel() ?: SolutionValidationModel.EMPTY
        } else SolutionValidationModel.EMPTY,
        unreadMessagesCount = unreadMessagesCount ?: DEFAULT_NOT_VALID_VALUE_INT
    )