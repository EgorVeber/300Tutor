package org.threehundredtutor.ui_common.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.launchJob(
    catchBlock: (t: Throwable) -> Unit,
    finallyBlock: (() -> Unit)? = null,
    context: CoroutineContext = Dispatchers.Default,
    tryBlock: suspend CoroutineScope.() -> Unit,
): Job {
    return launch(context + BaseCoroutineExceptionHandler(catchBlock)) {
        try {
            tryBlock()
        } finally {
            finallyBlock?.invoke()
        }
    }
}