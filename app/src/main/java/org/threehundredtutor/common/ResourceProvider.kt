package org.threehundredtutor.common

import androidx.annotation.StringRes

interface ResourceProvider {
    fun string(@StringRes id: Int, vararg formatArgs: Any?): String
}