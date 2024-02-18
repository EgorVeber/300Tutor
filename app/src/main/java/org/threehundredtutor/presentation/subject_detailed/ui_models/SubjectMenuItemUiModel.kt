package org.threehundredtutor.presentation.subject_detailed.ui_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.threehundredtutor.domain.subject_detailed.models.SubjectMenuItemType

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