package com.v_points.app.data.restore

import com.v_points.app.domain.restore.RestorePasswordModel
import com.v_points.app.ui_common.util.orFalse

fun RestoreResponse.toRestorePasswordModel(): RestorePasswordModel = RestorePasswordModel(
    isSucceeded = isSucceeded.orFalse(),
    message = message.orEmpty()
)