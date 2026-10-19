package com.v_points.app.domain.main.models

import com.v_points.app.presentation.main.ui_models.MainUiItem

data class ExtraButtonInfoModel(
    val color: String,
    val link: String,
    val text: String
) : MainUiItem