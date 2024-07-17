package org.threehundredtutor.ui_common.util

import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import kotlin.math.roundToInt

fun Int.fractionOf(value: Int): Float = (this.percentOf(value) / 100.0).toFloat()

fun Int.percentOf(value: Int): Int {
    return if (this == 0 || value == 0) 0
    else ((value.toDouble() / this) * 100).roundToInt()
}

fun Int?.orDefaultNotValidValue() = this ?: DEFAULT_NOT_VALID_VALUE_INT