package org.threehundredtutor.presentation.main_menu.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.presentation.main_menu.MainMenuItem.Companion.getIcon
import org.threehundredtutor.presentation.main_menu.MainMenuItem.Companion.getTitle
import org.threehundredtutor.ui_common.view_components.loadImage
import org.threehundredtutor.ui_core.databinding.MainMenuItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionImageItemBinding

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

data class ImageItem(val name: String, val path: String)

fun getImagesAdapted() =
    adapterDelegateViewBinding<ImageItem, ImageItem, SolutionImageItemBinding>({ layoutInflater, root ->
        SolutionImageItemBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            binding.solutionImage.loadImage(item.path)
        }
    }