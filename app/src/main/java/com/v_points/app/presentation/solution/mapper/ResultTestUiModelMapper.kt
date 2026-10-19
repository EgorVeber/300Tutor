package com.v_points.app.presentation.solution.mapper

import com.v_points.app.core.UiCorePlurals
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.domain.solution.models.points.SolutionPointsModel
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.solution.ui_models.ResultTestUiModel
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.util.addPercentSymbol
import com.v_points.app.ui_common.util.fractionOf
import com.v_points.app.ui_common.util.percentOf

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