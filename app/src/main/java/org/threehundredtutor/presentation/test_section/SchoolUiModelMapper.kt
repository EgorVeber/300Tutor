package org.threehundredtutor.presentation.test_section

import org.threehundredtutor.domain.school.GetSchoolModelItem

fun GetSchoolModelItem.toSchoolUiModel(checked: Boolean): SchoolUiModel = SchoolUiModel(
    id = id,
    hostUrl = hostUrl,
    name = name,
    checked = checked
)