package org.threehundredtutor.presentation.common

import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id: Int, vararg formatArgs: Any?): String
}