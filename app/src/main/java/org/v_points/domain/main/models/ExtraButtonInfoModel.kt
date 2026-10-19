package org.v_points.domain.main.models

import org.v_points.presentation.main.ui_models.MainUiItem

data class ExtraButtonInfoModel(
    val color: String,
    val link: String,
    val text: String
) : MainUiItem