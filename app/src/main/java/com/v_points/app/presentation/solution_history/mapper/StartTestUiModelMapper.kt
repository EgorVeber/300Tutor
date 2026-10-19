package com.v_points.app.presentation.solution_history.mapper

import com.v_points.app.domain.solution_history.models.SolutionHistoryItemModel
import com.v_points.app.presentation.solution_history.models.StartTestUiModel

fun SolutionHistoryItemModel.toStartTestUiModel(
    questionsCount: Int,
): StartTestUiModel =
    StartTestUiModel(
        testId = solutionInfoModel.testId,
        nameTest = solutionInfoModel.testName,
        questionsCount = questionsCount
    )