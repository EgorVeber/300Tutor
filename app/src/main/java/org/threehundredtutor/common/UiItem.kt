package org.threehundredtutor.common

interface UiItem {
    fun areItemsTheSame(oldItem: UiItem, newItem: UiItem): Boolean =
        oldItem.javaClass == newItem.javaClass

    fun areContentsTheSame(oldItem: UiItem, newItem: UiItem): Boolean = oldItem == newItem

    fun getChangePayload(oldItem: UiItem, newItem: UiItem): Collection<UiItemPayload>? = null
}