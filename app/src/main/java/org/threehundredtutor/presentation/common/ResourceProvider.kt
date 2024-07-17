package org.threehundredtutor.presentation.common

import androidx.annotation.StringRes
import androidx.core.location.LocationRequestCompat.Quality

interface ResourceProvider {

    fun string(@StringRes id: Int, vararg formatArgs: Any?): String
    fun quantityString(@Quality id: Int, number: Int, vararg formatArgs: Any?): String
}