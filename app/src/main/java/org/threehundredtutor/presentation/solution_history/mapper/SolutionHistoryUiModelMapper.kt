package org.threehundredtutor.presentation.solution_history.mapper

import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.solution_history.models.SolutionHistoryItemModel
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.solution_history.models.SolutionHistoryUiModel
import org.threehundredtutor.ui_common.util.addPercentSymbol

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
        pointWithTest = resourceProvider.string(
            UiCoreStrings.received_points_out_of,
            solutionValidationModel.studentTotalPointsString,
            solutionValidationModel.maxTotalPointsString
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