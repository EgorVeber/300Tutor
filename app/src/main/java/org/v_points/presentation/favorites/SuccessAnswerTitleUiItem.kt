package org.v_points.presentation.favorites

import org.v_points.presentation.solution.ui_models.SolutionUiItem

@JvmInline
value class SuccessAnswerTitleUiItem(val title: String) : SolutionUiItem


data class AnswerFavoritesWithErrorsResultUiItem(val answer: String, val title: String) :
    SolutionUiItem


@JvmInline
value class EmptyUiItem(val title: String) : SolutionUiItem