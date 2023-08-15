package org.threehundredtutor.common.utils

import android.content.Context
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {
    override fun string(id: Int): String = context.resources.getString(id)
}