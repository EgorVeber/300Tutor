package org.threehundredtutor.presentation.main_menu.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainMenuManager(
    mainMenuUiItemClickListener: (MainMenuUiItem) -> Unit
) : AsyncListDifferDelegationAdapter<MainMenuUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getMainMenuItemAdapted(mainMenuUiItemClickListener))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MainMenuUiItem>() {
            override fun areItemsTheSame(
                oldItem: MainMenuUiItem,
                newItem: MainMenuUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: MainMenuUiItem,
                newItem: MainMenuUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}