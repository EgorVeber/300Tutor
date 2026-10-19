package com.v_points.app.data.settings_app.model

import com.v_points.app.domain.settings_app.TelephoneInputOptionsModel
import com.v_points.app.ui_common.util.orFalse

fun TelephoneInputOptionsResponse.toTelephoneInputOptionsModel(): TelephoneInputOptionsModel =
    TelephoneInputOptionsModel(
        useInput = useInput.orFalse(),
    )