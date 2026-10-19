package org.v_points.presentation.solution_history.mapper

import org.v_points.domain.solution_history.models.SolutionHistoryItemModel
import org.v_points.presentation.solution_history.models.StartTestUiModel

fun SolutionHistoryItemModel.toStartTestUiModel(
    questionsCount: Int,
): StartTestUiModel =
    StartTestUiModel(
        testId = solutionInfoModel.testId,
        nameTest = solutionInfoModel.testName,
        questionsCount = questionsCount
    )