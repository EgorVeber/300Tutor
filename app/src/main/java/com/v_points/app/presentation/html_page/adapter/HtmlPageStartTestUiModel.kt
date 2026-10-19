package com.v_points.app.presentation.html_page.adapter

import com.v_points.app.presentation.solution.ui_models.SolutionUiItem

data class HtmlPageStartTestUiModel(
    val firstCount: String,
    val secondCount: String,
    val fullCount: String,
    val firstVisible: Boolean,
    val secondVisible: Boolean,
    val fullVisible: Boolean
) : SolutionUiItem