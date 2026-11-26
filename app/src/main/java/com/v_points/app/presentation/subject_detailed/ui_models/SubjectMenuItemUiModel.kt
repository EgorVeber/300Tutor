package com.v_points.app.presentation.subject_detailed.ui_models

import android.os.Parcelable
import com.v_points.app.domain.subject_detailed.models.SubjectMenuItemType
import kotlinx.parcelize.Parcelize

@Parcelize //TODO TutorAndroid-63
data class SubjectMenuItemUiModel(
    val subjectMenuItems: List<SubjectMenuItemUiModel>,
    val path: String,
    val text: String,
    val type: SubjectMenuItemType,
    val workSpaceId: String
) : SubjectDetailedUiItem, Parcelable {
    companion object {

        val EMPTY = SubjectMenuItemUiModel(
            listOf(),
            "",
            "",
            SubjectMenuItemType.UNKNOWN,
            ""
        )
    }
}