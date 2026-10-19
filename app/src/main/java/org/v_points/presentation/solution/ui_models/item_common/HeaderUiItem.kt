package org.v_points.presentation.solution.ui_models.item_common

import org.v_points.presentation.solution.ui_models.SolutionUiItem

data class HeaderUiItem(
    val questionId: String,
    val questionNumber: String,
    val isQuestionLikedByStudent: Boolean
) : SolutionUiItem