package org.threehundredtutor.data.restore

import org.threehundredtutor.domain.restore.RestorePasswordModel
import org.threehundredtutor.ui_common.util.orFalse

fun RestoreResponse.toRestorePasswordModel(): RestorePasswordModel = RestorePasswordModel(
    isSucceeded = isSucceeded.orFalse(),
    message = message.orEmpty()
)