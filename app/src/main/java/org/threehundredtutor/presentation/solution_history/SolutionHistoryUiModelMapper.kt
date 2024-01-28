package org.threehundredtutor.presentation.solution_history

import org.threehundredtutor.R
import org.threehundredtutor.common.addPercent
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.domain.solution_history.models.SolutionHistoryItemModel

fun SolutionHistoryItemModel.toSolutionHistoryUiModel(resourceProvider: ResourceProvider): SolutionHistoryUiModel =
    SolutionHistoryUiModel(
        id = solutionInfoModel.id,
        testId = solutionInfoModel.testId,
        nameTest = solutionInfoModel.testName,
        isFinished = solutionInfoModel.isFinished,
        startTestDate = resourceProvider.string(
            R.string.start_date,
            solutionInfoModel.startedOnUtc
        ),
        endTestDate = resourceProvider.string(R.string.end_date, solutionInfoModel.finishedOnUtc),
        pointWithTest = resourceProvider.string(
            R.string.received_points_out_of,
            solutionValidationModel.studentTotalPointsString,
            solutionValidationModel.maxTotalPointsString
        ),
        testIsSolvedPercent = resourceProvider.string(
            R.string.test_solved,
            solutionValidationModel.testIsSolvedPercent.toString().addPercent()
        ),
        issuesResolvedPercent = resourceProvider.string(
            R.string.of_issues_resolved,
            solutionValidationModel.issuesResolvedPercent.toString().addPercent()
        ),
        maxTotalPoints = solutionValidationModel.maxTotalPoints,
        maxTotalPointsString = solutionValidationModel.maxTotalPointsString,
        studentTotalPoints = solutionValidationModel.studentTotalPoints,
        studentTotalPointsString = solutionValidationModel.studentTotalPointsString,
        validatedQuestionsCount = solutionValidationModel.validatedQuestionsCount.toString(),
        hasRightAnswerQuestionsCount = solutionValidationModel.hasRightAnswerQuestionsCount.toString(),
        questionsCount = solutionValidationModel.questionsCount
    )