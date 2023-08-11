package org.threehundredtutor.common.utils

import androidx.annotation.StringRes

interface ResourceProvider {
    fun string(@StringRes id: Int): String
}