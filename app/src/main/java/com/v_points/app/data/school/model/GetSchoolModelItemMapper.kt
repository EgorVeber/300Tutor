package com.v_points.app.data.school.model

import com.v_points.app.domain.school.GetSchoolModelItem

fun GetSchoolResponseItem.toGetSchoolModelItem(): GetSchoolModelItem = GetSchoolModelItem(
    id = id.orEmpty(),
    hostUrl = hostUrl.orEmpty(),
    name = name.orEmpty()
)