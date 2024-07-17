package org.threehundredtutor.presentation.solution_history.mapper

import org.threehundredtutor.domain.solution_history.models.SolutionHistoryItemModel
import org.threehundredtutor.presentation.solution_history.models.StartTestUiModel

fun SolutionHistoryItemModel.toStartTestUiModel(
    questionsCount: Int,
): StartTestUiModel =
    StartTestUiModel(
        testId = solutionInfoModel.testId,
        nameTest = solutionInfoModel.testName,
        questionsCount = questionsCount
    )