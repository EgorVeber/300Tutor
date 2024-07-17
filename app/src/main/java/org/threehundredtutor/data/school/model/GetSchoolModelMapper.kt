package org.threehundredtutor.data.school.model

import org.threehundredtutor.domain.school.GetSchoolModelItem

fun GetSchoolResponse.toGetSchoolModel(): List<GetSchoolModelItem> =
    map { schoolItem -> schoolItem.toGetSchoolModelItem() }