package org.threehundredtutor.ui_common.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class BaseCoroutineExceptionHandler(private val catchBlock: (t: Throwable) -> Unit) :
    AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        catchBlock(exception)
    }
}