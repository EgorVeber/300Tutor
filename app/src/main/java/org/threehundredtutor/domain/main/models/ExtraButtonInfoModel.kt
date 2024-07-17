package org.threehundredtutor.domain.main.models

import org.threehundredtutor.presentation.main.ui_models.MainUiItem

data class ExtraButtonInfoModel(
    val color: String,
    val link: String,
    val text: String
) : MainUiItem