package com.v_points.app.presentation.test_section

import com.v_points.app.domain.school.GetSchoolModelItem

fun GetSchoolModelItem.toSchoolUiModel(checked: Boolean): SchoolUiModel = SchoolUiModel(
    id = id,
    hostUrl = hostUrl,
    name = name,
    checked = checked
)