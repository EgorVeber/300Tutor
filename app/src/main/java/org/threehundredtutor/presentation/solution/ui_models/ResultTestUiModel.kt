package org.threehundredtutor.presentation.solution.ui_models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultTestUiModel(
    val resultTestRightQuestion: String,
    val questionCountNeedCheckText: String,
    val titlePoint: String,
    val resultPercent: String,
    val fractionAnswer: Float,
) : Parcelable