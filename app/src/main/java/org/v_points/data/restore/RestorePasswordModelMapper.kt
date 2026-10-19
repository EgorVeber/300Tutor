package org.v_points.data.restore

import org.v_points.domain.restore.RestorePasswordModel
import org.v_points.ui_common.util.orFalse

fun RestoreResponse.toRestorePasswordModel(): RestorePasswordModel = RestorePasswordModel(
    isSucceeded = isSucceeded.orFalse(),
    message = message.orEmpty()
)