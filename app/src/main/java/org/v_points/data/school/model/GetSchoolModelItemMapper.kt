package org.v_points.data.school.model

import org.v_points.domain.school.GetSchoolModelItem

fun GetSchoolResponseItem.toGetSchoolModelItem(): GetSchoolModelItem = GetSchoolModelItem(
    id = id.orEmpty(),
    hostUrl = hostUrl.orEmpty(),
    name = name.orEmpty()
)