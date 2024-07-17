package org.threehundredtutor.presentation.html_page.adapter

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.ui_core.databinding.HtmlPageEmptyItemBinding
import org.threehundredtutor.ui_core.databinding.HtmlPageHeaderItemBinding
import org.threehundredtutor.ui_core.databinding.HtmlPageStartTestDirectoryItemBinding

object HtmlPageAdapters {
    fun getHtmlPageStartTestAdapter(
        firstStartTestClickListener: (Unit) -> Unit,
        secondStartTestClickListener: (Unit) -> Unit,
        fullStartTestClickListener: (Unit) -> Unit
    ) =
        adapterDelegateViewBinding<HtmlPageStartTestUiModel, SolutionUiItem, HtmlPageStartTestDirectoryItemBinding>(
            { layoutInflater, root ->
                HtmlPageStartTestDirectoryItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.firstPartButton.setOnClickListener { firstStartTestClickListener.invoke(Unit) }
            binding.secondPartButton.setOnClickListener { secondStartTestClickListener.invoke(Unit) }
            binding.fullTestButton.setOnClickListener { fullStartTestClickListener.invoke(Unit) }
            bind {
                binding.firstPartButton.isVisible = item.firstVisible
                binding.firstPartButton.text =
                    getString(UiCoreStrings.html_page_first_path, item.firstCount)
                binding.secondPartButton.isVisible = item.secondVisible
                binding.secondPartButton.text =
                    getString(UiCoreStrings.html_page_second_path, item.secondCount)
                binding.fullTestButton.isVisible = item.fullVisible
                binding.fullTestButton.text =
                    getString(UiCoreStrings.html_page_full_text, item.fullCount)
            }
        }

    fun getHtmlPageHeaderEmptyItemAdapter() =
        adapterDelegateViewBinding<HtmlPageHeaderItem, SolutionUiItem, HtmlPageHeaderItemBinding>(
            { layoutInflater, root ->
                HtmlPageHeaderItemBinding.inflate(layoutInflater, root, false)
            }) {}

    fun getHtmlPageEmptyUiItem() =
        adapterDelegateViewBinding<HtmlPageEmptyUiItem, SolutionUiItem, HtmlPageEmptyItemBinding>(
            { layoutInflater, root ->
                HtmlPageEmptyItemBinding.inflate(layoutInflater, root, false)
            }) {}
}