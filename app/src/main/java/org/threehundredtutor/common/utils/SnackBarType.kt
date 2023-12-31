package org.threehundredtutor.common.utils

import androidx.annotation.AttrRes
import org.threehundredtutor.R

enum class SnackBarType(@AttrRes val colorRes: Int) {
    INFO(R.attr.primary), SUCCESS(R.attr.defaultGreen), ERROR(R.attr.warning);
}


