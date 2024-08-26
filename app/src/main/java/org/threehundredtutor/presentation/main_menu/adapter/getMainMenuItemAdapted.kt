package org.threehundredtutor.presentation.main_menu.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.core.UiCoreDrawable
import org.threehundredtutor.presentation.main_menu.MainMenuItem.Companion.getIcon
import org.threehundredtutor.presentation.main_menu.MainMenuItem.Companion.getTitle
import org.threehundredtutor.ui_core.databinding.MainMenuItemBinding
import org.threehundredtutor.ui_core.databinding.PhotoItemViewBinding

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

fun getPhotoAdapted(mainMenuUiItemClickListener: () -> Unit) =
    adapterDelegateViewBinding<BannerItem, BannerItem, PhotoItemViewBinding>({ layoutInflater, root ->
        PhotoItemViewBinding.inflate(layoutInflater, root, false)
    }) {
        binding.root.setOnClickListener {
            mainMenuUiItemClickListener.invoke()
        }
        bind {
            binding.rootItemView.loadImage(item.path, UiCoreDrawable.banner_1000x349,item.index)
        }
    }