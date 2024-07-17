package org.threehundredtutor.ui_common.flow

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

@Suppress("FunctionName")
fun <T> SingleSharedFlow(): MutableSharedFlow<T> = MutableSharedFlow(
    replay = 0,
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
)

inline fun <reified T> Flow<T>.observeFlow(
    fragment: Fragment,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit,
): Job = fragment.viewLifecycleOwner.lifecycleScope.launchWhenStarted {
    flowWithLifecycle(fragment.viewLifecycleOwner.lifecycle, state).collect { item ->
        action(item)
    }
}