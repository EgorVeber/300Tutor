package org.v_points.domain.subject_detailed.models

data class MenuSubjectConfigModel(
    val text: String,
    val type: SubjectMenuItemType,
    val path: String,
    val workSpaceId: String,
    val subjectMenuItems: List<MenuSubjectConfigModel>
)