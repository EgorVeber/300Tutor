package org.v_points.presentation.test_section

import org.v_points.domain.school.GetSchoolModelItem

fun GetSchoolModelItem.toSchoolUiModel(checked: Boolean): SchoolUiModel = SchoolUiModel(
    id = id,
    hostUrl = hostUrl,
    name = name,
    checked = checked
)