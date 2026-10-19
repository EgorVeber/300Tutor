package org.v_points.data.school.model

import org.v_points.domain.school.GetSchoolModelItem

fun GetSchoolResponse.toGetSchoolModel(): List<GetSchoolModelItem> =
    map { schoolItem -> schoolItem.toGetSchoolModelItem() }