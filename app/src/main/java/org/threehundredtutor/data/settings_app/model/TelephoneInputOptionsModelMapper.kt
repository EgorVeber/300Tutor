package org.threehundredtutor.data.settings_app.model

import org.threehundredtutor.domain.settings_app.TelephoneInputOptionsModel
import org.threehundredtutor.ui_common.util.orFalse

fun TelephoneInputOptionsResponse.toTelephoneInputOptionsModel(): TelephoneInputOptionsModel =
    TelephoneInputOptionsModel(
        useInput = useInput.orFalse(),
    )