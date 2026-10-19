package org.v_points.data.settings_app.model

import org.v_points.domain.settings_app.TelephoneInputOptionsModel
import org.v_points.ui_common.util.orFalse

fun TelephoneInputOptionsResponse.toTelephoneInputOptionsModel(): TelephoneInputOptionsModel =
    TelephoneInputOptionsModel(
        useInput = useInput.orFalse(),
    )