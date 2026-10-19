package com.v_points.app.data.school.model

import com.v_points.app.domain.school.GetSchoolModelItem

fun GetSchoolResponse.toGetSchoolModel(): List<GetSchoolModelItem> =
    map { schoolItem -> schoolItem.toGetSchoolModelItem() }