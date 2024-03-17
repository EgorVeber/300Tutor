package org.threehundredtutor.common

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {
    override fun string(@StringRes id: Int, vararg formatArgs: Any?): String =
        context.resources.getString(id, *formatArgs)
}