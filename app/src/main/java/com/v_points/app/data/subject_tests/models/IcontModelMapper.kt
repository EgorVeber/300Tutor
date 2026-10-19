package com.v_points.app.data.subject_tests.models

import com.v_points.app.domain.subject_tests.models.IconModel

fun IconResponse.toIconModel(): IconModel = IconModel(
    id = id.orEmpty(),
    name = name.orEmpty(),
    fileId = fileId ?: -1,
    setId = setId.orEmpty()
)