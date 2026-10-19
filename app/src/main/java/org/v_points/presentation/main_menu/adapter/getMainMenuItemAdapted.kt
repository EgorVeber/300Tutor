package org.v_points.presentation.main_menu.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.v_points.presentation.main_menu.MainMenuItem.Companion.getIcon
import org.v_points.presentation.main_menu.MainMenuItem.Companion.getTitle
import org.v_points.ui_core.databinding.MainMenuItemBinding

fun getMainMenuItemAdapted(mainMenuUiItemClickListener: (MainMenuUiItem) -> Unit) =
    adapterDelegateViewBinding<MainMenuUiItem, MainMenuUiItem, MainMenuItemBinding>({ layoutInflater, root ->
        MainMenuItemBinding.inflate(layoutInflater, root, false)
    }) {
        binding.menuItemContainer.setOnClickListener {
            mainMenuUiItemClickListener.invoke(item)
        }
        bind {
            binding.title.setText(item.mainMenuItem.getTitle())
            binding.icon.setImageResource(item.mainMenuItem.getIcon())
        }
    }