package org.threehundredtutor.presentation.solution.mapper

import org.threehundredtutor.R
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.addPercent
import org.threehundredtutor.common.fractionOf
import org.threehundredtutor.common.percentOf
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel
import org.threehundredtutor.presentation.solution.ui_models.ResultTestUiModel

fun SolutionPointsModel.toResultTestUiModel(resourceProvider: ResourceProvider): ResultTestUiModel =
    ResultTestUiModel(
        resultTestRightQuestion = resourceProvider.string(
            R.string.you_answered_out_of_questions_correctly,
            hasRightAnswerQuestionsCount,
            questionsCount
        ),
        questionCountNeedCheckText = if (questionCountNeedCheck > 0)
            resourceProvider.string(
                R.string.result_test_need_to_check_questions,
                questionCountNeedCheck,
            ) else EMPTY_STRING,
        titlePoint = resourceProvider.string(
            R.string.pie_chat_title_point,
            studentTotalPoints.toString(),
            maxTotalPoints.toString()
        ),
        resultPercent = resourceProvider.string(
            R.string.test_solved,
            maxTotalPoints.percentOf(studentTotalPoints).toString().addPercent()
        ),
        fractionAnswer = maxTotalPoints.fractionOf(studentTotalPoints)
    )