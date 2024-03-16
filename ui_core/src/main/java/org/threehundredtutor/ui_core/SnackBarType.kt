package org.threehundredtutor.ui_core

import androidx.annotation.AttrRes

enum class SnackBarType(@AttrRes val colorRes: Int) {
    INFO(R.attr.primary), SUCCESS(R.attr.defaultGreen), ERROR(R.attr.warning);
}


