package org.v_points.data.subject_tests.models

import org.v_points.domain.subject_tests.models.IconModel

fun IconResponse.toIconModel(): IconModel = IconModel(
    id = id.orEmpty(),
    name = name.orEmpty(),
    fileId = fileId ?: -1,
    setId = setId.orEmpty()
)