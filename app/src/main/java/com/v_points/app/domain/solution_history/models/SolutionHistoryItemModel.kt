package com.v_points.app.domain.solution_history.models

data class SolutionHistoryItemModel(
    val solutionInfoModel: SolutionInfoModel,
    val solutionValidationModel: SolutionValidationModel,
    val unreadMessagesCount: Int
)