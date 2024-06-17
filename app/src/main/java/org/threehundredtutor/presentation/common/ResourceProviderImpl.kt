package org.threehundredtutor.presentation.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.location.LocationRequestCompat.Quality
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {

    override fun string(@StringRes id: Int, vararg formatArgs: Any?): String =
        context.resources.getString(id, *formatArgs)

    override fun quantityString(@Quality id: Int, number: Int, vararg formatArgs: Any?): String =
        context.resources.getQuantityString(id, number, *formatArgs)
}