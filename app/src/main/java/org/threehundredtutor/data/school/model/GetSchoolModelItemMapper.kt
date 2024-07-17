package org.threehundredtutor.data.school.model

import org.threehundredtutor.domain.school.GetSchoolModelItem

fun GetSchoolResponseItem.toGetSchoolModelItem(): GetSchoolModelItem = GetSchoolModelItem(
    id = id.orEmpty(),
    hostUrl = hostUrl.orEmpty(),
    name = name.orEmpty()
)