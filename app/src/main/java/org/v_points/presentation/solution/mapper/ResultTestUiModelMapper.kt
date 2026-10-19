package org.v_points.presentation.solution.mapper

import org.v_points.core.UiCorePlurals
import org.v_points.core.UiCoreStrings
import org.v_points.domain.solution.models.points.SolutionPointsModel
import org.v_points.presentation.common.ResourceProvider
import org.v_points.presentation.solution.ui_models.ResultTestUiModel
import org.v_points.ui_common.EMPTY_STRING
import org.v_points.ui_common.util.addPercentSymbol
import org.v_points.ui_common.util.fractionOf
import org.v_points.ui_common.util.percentOf

fun SolutionPointsModel.toResultTestUiModel(
    resourceProvider: ResourceProvider,
    imagePath: String
): ResultTestUiModel =
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
        fractionAnswer = maxTotalPoints.fractionOf(studentTotalPoints),
        imagePath = imagePath
    )