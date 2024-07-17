package org.threehundredtutor.presentation.solution.mapper

import org.threehundredtutor.core.UiCorePlurals
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.solution.ui_models.ResultTestUiModel
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.util.addPercentSymbol
import org.threehundredtutor.ui_common.util.fractionOf
import org.threehundredtutor.ui_common.util.percentOf

fun SolutionPointsModel.toResultTestUiModel(resourceProvider: ResourceProvider): ResultTestUiModel =
    ResultTestUiModel(
        resultTestRightQuestion = resourceProvider.quantityString(
            UiCorePlurals.result_test_you_answered_out_of_questions_correctly,
            questionsCount,
            questionsCount,
            hasRightAnswerQuestionsCount,
        ),
        questionCountNeedCheckText =
        when {
            questionCountNeedCheck == 1 -> {
                resourceProvider.quantityString(
                    UiCorePlurals.result_test_need_to_check_questions,
                    questionCountNeedCheck, questionCountNeedCheck
                ) + resourceProvider.string(UiCoreStrings.result_test_need_to_check_question)
            }

            questionCountNeedCheck > 1 -> {
                resourceProvider.quantityString(
                    UiCorePlurals.result_test_need_to_check_questions,
                    questionCountNeedCheck, questionCountNeedCheck
                ) + resourceProvider.string(UiCoreStrings.result_test_need_to_check_questions)
            }

            else -> EMPTY_STRING
        },
        titlePoint = resourceProvider.string(
            UiCoreStrings.pie_chat_title_point,
            studentTotalPoints.toString(),
            maxTotalPoints.toString()
        ),
        resultPercent = resourceProvider.string(
            UiCoreStrings.test_solved,
            maxTotalPoints.percentOf(studentTotalPoints).toString().addPercentSymbol()
        ),
        fractionAnswer = maxTotalPoints.fractionOf(studentTotalPoints)
    )