package com.v_points.app.presentation.solution.ui_models.item_common

import com.v_points.app.presentation.solution.ui_models.SolutionUiItem

data class HeaderUiItem(
    val questionId: String,
    val questionNumber: String,
    val isQuestionLikedByStudent: Boolean
) : SolutionUiItem