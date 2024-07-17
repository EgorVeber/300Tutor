package org.threehundredtutor.data.subject_tests.models

import org.threehundredtutor.domain.subject_tests.models.IconModel

fun IconResponse.toIconModel(): IconModel = IconModel(
    id = id.orEmpty(),
    name = name.orEmpty(),
    fileId = fileId ?: -1,
    serverPath = serverPath.orEmpty(),
    setId = setId.orEmpty()
)