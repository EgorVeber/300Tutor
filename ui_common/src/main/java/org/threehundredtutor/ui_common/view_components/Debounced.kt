package org.threehundredtutor.ui_common.view_components

import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton

private const val DEFAULT_GLOBAL_DEBOUNCE_200: Long = 200
private const val TIME_0: Long = 0

fun View.setDebouncedClickListener(
    debounceIntervalMs: Long = DEFAULT_GLOBAL_DEBOUNCE_200,
    listener: (view: View) -> Unit
) {

    var lastTapTimestamp: Long = TIME_0
    val customListener = View.OnClickListener {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTapTimestamp > debounceIntervalMs) {
            lastTapTimestamp = currentTime
            listener(it)
        }
    }
    this.setOnClickListener(customListener)
}

fun CheckBox.setDebouncedCheckedChangeListener(
    listener: (view: View, isChecked: Boolean) -> Unit
) {
    var lastChangeTimestamp: Long = TIME_0
    val debounceIntervalMs: Long = DEFAULT_GLOBAL_DEBOUNCE_200

    val customListener = CompoundButton.OnCheckedChangeListener { view, isChecked ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastChangeTimestamp > debounceIntervalMs) {
            lastChangeTimestamp = currentTime
            listener(view, isChecked)
        }
    }

    this.setOnCheckedChangeListener(customListener)
}