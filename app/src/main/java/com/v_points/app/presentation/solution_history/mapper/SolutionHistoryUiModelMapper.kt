package com.v_points.app.presentation.solution_history.mapper

import com.v_points.app.core.UiCorePlurals
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.domain.solution_history.models.SolutionHistoryItemModel
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.solution_history.models.SolutionHistoryUiModel
import com.v_points.app.ui_common.util.addPercentSymbol

fun SolutionHistoryItemModel.toSolutionHistoryUiModel(
    resourceProvider: ResourceProvider,
    questionsCount: Int,
): SolutionHistoryUiModel =
    SolutionHistoryUiModel(
        solutionId = solutionInfoModel.id,
        testId = solutionInfoModel.testId,
        nameTest = solutionInfoModel.testName,
        isFinished = solutionInfoModel.isFinished,
        startTestDate = resourceProvider.string(
            UiCoreStrings.start_date,
            solutionInfoModel.startedOnUtc
        ),
        endTestDate = resourceProvider.string(
            UiCoreStrings.end_date,
            solutionInfoModel.finishedOnUtc
        ),
        pointWithTest = resourceProvider.quantityString(
            UiCorePlurals.result_test_history_point,
            solutionValidationModel.studentTotalPoints,
            solutionValidationModel.studentTotalPoints,
            solutionValidationModel.maxTotalPoints
        ),
        testIsSolvedPercent = resourceProvider.string(
            UiCoreStrings.test_solved,
            solutionValidationModel.testIsSolvedPercent.toString().addPercentSymbol()
        ),
        issuesResolvedPercent = resourceProvider.string(
            UiCoreStrings.of_issues_resolved,
            solutionValidationModel.issuesResolvedPercent.toString().addPercentSymbol()
        ),
        maxTotalPoints = solutionValidationModel.maxTotalPoints,
        maxTotalPointsString = solutionValidationModel.maxTotalPointsString,
        studentTotalPoints = solutionValidationModel.studentTotalPoints,
        studentTotalPointsString = solutionValidationModel.studentTotalPointsString,
        validatedQuestionsCount = solutionValidationModel.validatedQuestionsCount.toString(),
        hasRightAnswerQuestionsCount = solutionValidationModel.hasRightAnswerQuestionsCount.toString(),
        questionsCount = questionsCount
        //solutionValidationModel.questionsCount //Возможно другой запрос надо для не завершенных тестов questionsCount -1, поэтому берем c предыдущего фрагмента
    )